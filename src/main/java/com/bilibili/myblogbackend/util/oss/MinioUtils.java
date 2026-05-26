package com.bilibili.myblogbackend.util.oss;

import io.minio.ListPartsResponse;
import io.minio.PutObjectArgs;
import io.minio.UploadPartResponse;
import io.minio.errors.*;
import io.minio.messages.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MinioUtils {

    private final CustomMinioClient customMinioClient;
    private final MinioProperties minioProperties;
    private final ThreadPoolTaskExecutor threadPool;

    // 分片大小 10MB
    private static final long CHUNK_SIZE =2* 5 * 1024 * 1024;
    // 上传超时时间（分钟）
    private static final long UPLOAD_TIMEOUT_MINUTES = 30;

    /**
     * 上传图片（单文件直接上传）
     *
     * @param fileName 文件名
     * @param file     文件
     */
    public void uploadImage(String fileName, MultipartFile file) {
        long startTime = System.currentTimeMillis();
        String originalFilename = file.getOriginalFilename();
        long fileSize = file.getSize();
        String contentType = file.getContentType();
        String objectName = minioProperties.getImage() + fileName;

        log.info("[图片上传] 开始上传 | 文件名: {} | 原始文件名: {} | 大小: {} bytes | ContentType: {}",
                fileName, originalFilename, fileSize, contentType);

        try (InputStream inputStream = file.getInputStream()) {
            customMinioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioProperties.getBucketsName())
                            .object(objectName)
                            .stream(inputStream, fileSize, -1)
                            .contentType(contentType)
                            .build()
            );

            long duration = System.currentTimeMillis() - startTime;
            log.info("[图片上传] 上传成功 | 文件名: {} | 耗时: {} ms | 大小: {} bytes | 平均速度: {} KB/s",
                    fileName, duration, fileSize,
                    fileSize > 0 ? String.format("%.2f", fileSize / 1024.0 / (duration / 1000.0)) : "N/A");

        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            log.error("[图片上传] 上传失败 | 文件名: {} | 耗时: {} ms | 错误: {}",
                    fileName, duration, e.getMessage(), e);
            throw new RuntimeException("图片上传失败: " + e.getMessage(), e);
        }
    }

    /**
     * 分片上传大文件（视频等）
     *
     * @param fileName 文件名
     * @param file     文件
     */
    public void uploadPartFile(String fileName, MultipartFile file) {
        long totalStartTime = System.currentTimeMillis();
        String originalFilename = file.getOriginalFilename();
        long totalLength = file.getSize();
        String contentType = file.getContentType();
        String finalName = minioProperties.getVideo() + fileName;

        log.info("[分片上传] 开始上传 | 文件名: {} | 原始文件名: {} | 总大小: {} bytes ({:.2f} MB) | ContentType: {}",
                fileName, originalFilename, totalLength, totalLength / (1024.0 * 1024.0), contentType);

        // 预先将整个文件读入内存，避免每个分片重复打开 InputStream
        byte[] fileData;
        try {
            fileData = file.getBytes();
            log.debug("[分片上传] 文件已读入内存 | 文件名: {} | 内存占用: {} bytes", fileName, fileData.length);
        } catch (IOException e) {
            log.error("[分片上传] 读取文件失败 | 文件名: {} | 错误: {}", fileName, e.getMessage(), e);
            throw new RuntimeException("读取文件失败: " + e.getMessage(), e);
        }

        try {
            String uploadId = getUploadId(finalName, contentType);
            long chunkCount = (totalLength + CHUNK_SIZE - 1) / CHUNK_SIZE;

            log.info("[分片上传] 初始化完成 | 文件名: {} | uploadId: {} | 分片数: {} | 分片大小: {} bytes",
                    fileName, uploadId, chunkCount, CHUNK_SIZE);

            // CountDownLatch 改为局部变量，避免多并发干扰
            CountDownLatch countDownLatch = new CountDownLatch((int) chunkCount);

            // 提交所有分片上传任务
            for (int i = 0; i < chunkCount; i++) {
                final int chunkIndex = i + 1; // MinIO partNumber 从 1 开始
                final long position = (long) i * CHUNK_SIZE;
                final int readSize = (int) Math.min(CHUNK_SIZE, totalLength - position);
                final byte[] chunkData = new byte[readSize];
                System.arraycopy(fileData, (int) position, chunkData, 0, readSize);

                threadPool.submit(() -> {
                    long chunkStartTime = System.currentTimeMillis();
                    try {
                        processChunk(chunkIndex, finalName, chunkData, uploadId);
                        long chunkDuration = System.currentTimeMillis() - chunkStartTime;
                        log.debug("[分片上传] 分片上传成功 | 文件名: {} | 分片: {}/{} | 大小: {} bytes | 耗时: {} ms",
                                fileName, chunkIndex, chunkCount, readSize, chunkDuration);
                    } catch (Exception e) {
                        long chunkDuration = System.currentTimeMillis() - chunkStartTime;
                        log.error("[分片上传] 分片上传失败 | 文件名: {} | 分片: {}/{} | 耗时: {} ms | 错误: {}",
                                fileName, chunkIndex, chunkCount, chunkDuration, e.getMessage(), e);
                        throw new RuntimeException("分片 " + chunkIndex + " 上传失败: " + e.getMessage(), e);
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            }

            // 等待所有分片上传完成，带超时控制
            boolean completed = countDownLatch.await(UPLOAD_TIMEOUT_MINUTES, TimeUnit.MINUTES);
            if (!completed) {
                log.error("[分片上传] 上传超时 | 文件名: {} | 超时时间: {} 分钟", fileName, UPLOAD_TIMEOUT_MINUTES);
                throw new TimeoutException("分片上传超时，已等待 " + UPLOAD_TIMEOUT_MINUTES + " 分钟");
            }

            log.info("[分片上传] 所有分片上传完成 | 文件名: {} | 正在合并...", fileName);

            // 合并分片
            mergeFile(finalName, uploadId, chunkCount);

            long totalDuration = System.currentTimeMillis() - totalStartTime;
            log.info("[分片上传] 上传成功 | 文件名: {} | 总耗时: {} ms | 总大小: {} bytes ({:.2f} MB) | 平均速度: {} KB/s",
                    fileName, totalDuration, totalLength, totalLength / (1024.0 * 1024.0),
                    totalLength > 0 ? String.format("%.2f", totalLength / 1024.0 / (totalDuration / 1000.0)) : "N/A");

        } catch (Exception e) {
            long totalDuration = System.currentTimeMillis() - totalStartTime;
            log.error("[分片上传] 上传失败 | 文件名: {} | 总耗时: {} ms | 错误: {}",
                    fileName, totalDuration, e.getMessage(), e);
            throw new RuntimeException("分片上传失败: " + e.getMessage(), e);
        }
    }

    /**
     * 处理单个分片上传
     */
    private void processChunk(int chunkIndex, String finalName, byte[] chunkData, String uploadId)
            throws IOException, ServerException, InsufficientDataException, ErrorResponseException,
            NoSuchAlgorithmException, InvalidKeyException, XmlParserException, InvalidResponseException, InternalException {

        com.google.common.collect.Multimap<String, String> headers = com.google.common.collect.HashMultimap.create();
        headers.put("Content-Type", "application/octet-stream");

        UploadPartResponse uploadPartResponse = customMinioClient.uploadMultiPart(
                minioProperties.getBucketsName(),
                null,
                finalName,
                chunkData,
                chunkData.length,
                uploadId,
                chunkIndex,
                headers,
                null
        );

        log.debug("[分片上传] 分片详情 | 分片: {} | 大小: {} bytes | ETag: {}",
                chunkIndex, chunkData.length, uploadPartResponse.etag());
    }

    /**
     * 合并分片
     */
    private void mergeFile(String finalName, String uploadId, long chunkCount)
            throws ServerException, InsufficientDataException, ErrorResponseException, IOException,
            NoSuchAlgorithmException, InvalidKeyException, XmlParserException, InvalidResponseException, InternalException {

        long mergeStartTime = System.currentTimeMillis();
        Part[] parts = new Part[(int) chunkCount];
        ListPartsResponse partResult = customMinioClient.listMultipart(
                minioProperties.getBucketsName(),
                null,
                finalName,
                10000,
                0,
                uploadId,
                null,
                null
        );

        int partNumber = 1;
        for (Part part : partResult.result().partList()) {
            parts[partNumber - 1] = new Part(partNumber, part.etag());
            partNumber++;
        }

        customMinioClient.mergeMultipartUpload(
                minioProperties.getBucketsName(),
                null,
                finalName,
                uploadId,
                parts,
                null,
                null
        );

        long mergeDuration = System.currentTimeMillis() - mergeStartTime;
        log.info("[分片上传] 合并完成 | 文件名: {} | 合并耗时: {} ms | 分片数: {}",
                finalName, mergeDuration, chunkCount);
    }

    /**
     * 获取上传 ID
     */
    private String getUploadId(String finalName, String contentType)
            throws ServerException, InsufficientDataException, ErrorResponseException, IOException,
            NoSuchAlgorithmException, InvalidKeyException, XmlParserException, InvalidResponseException, InternalException {

        com.google.common.collect.Multimap<String, String> headers = com.google.common.collect.HashMultimap.create();
        headers.put("Content-Type", contentType);
        return customMinioClient.initMultiPartUpload(
                minioProperties.getBucketsName(),
                null,
                finalName,
                headers,
                null
        );
    }
}
