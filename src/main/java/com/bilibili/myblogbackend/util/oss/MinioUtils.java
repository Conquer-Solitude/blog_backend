/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.util.oss.CustomMinioClient
 *  com.bilibili.myblogbackend.util.oss.MinioProperties
 *  com.bilibili.myblogbackend.util.oss.MinioUtils
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Multimap
 *  io.minio.ListPartsResponse
 *  io.minio.PutObjectArgs
 *  io.minio.PutObjectArgs$Builder
 *  io.minio.UploadPartResponse
 *  io.minio.errors.ErrorResponseException
 *  io.minio.errors.InsufficientDataException
 *  io.minio.errors.InternalException
 *  io.minio.errors.InvalidResponseException
 *  io.minio.errors.ServerException
 *  io.minio.errors.XmlParserException
 *  io.minio.messages.Part
 *  lombok.Generated
 *  org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
 *  org.springframework.stereotype.Component
 *  org.springframework.web.multipart.MultipartFile
 */
package com.bilibili.myblogbackend.util.oss;

import com.bilibili.myblogbackend.util.oss.CustomMinioClient;
import com.bilibili.myblogbackend.util.oss.MinioProperties;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.minio.ListPartsResponse;
import io.minio.PutObjectArgs;
import io.minio.UploadPartResponse;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.messages.Part;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CountDownLatch;
import lombok.Generated;
import lombok.SneakyThrows;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MinioUtils {
    private final CustomMinioClient customMinioClient;
    private final MinioProperties minioProperties;
    private final ThreadPoolTaskExecutor threadPool;
    static final long CHUNK_SIZE = 0x500000L;
    private static CountDownLatch countDownLatch;

    @SneakyThrows
    public void uploadImage(String fileName, MultipartFile file){
        String contentType = file.getContentType();
        this.customMinioClient
                .putObject((PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(this.minioProperties.getBucketsName())).object(this.minioProperties.getImage() + fileName)).stream(file.getInputStream(), file.getSize(), -1L).contentType(contentType).build());
    }

    @SneakyThrows
    public void uploadPartFile(String fileName, MultipartFile file) {
        String contentType = file.getContentType();
        String finalName = this.minioProperties.getVideo() + fileName;
        String uploadId = this.getUploadId(finalName,contentType);
        long totalLength = file.getSize();
        long chunkCount = (totalLength + 0x500000L - 1L) / 0x500000L;
        countDownLatch = new CountDownLatch((int)chunkCount);
        int i = 0;
        while ((long)i < chunkCount) {
            long position = (long)i * 0x500000L;
            int readSize = (int)Math.min(0x500000L, totalLength - position);
            int test = i + 1;
            this.threadPool.submit(() -> {
                try {
                    this.processChunk(test, finalName, file, position, readSize, uploadId);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            ++i;
        }
        countDownLatch.await();
        System.out.println("\u53d1\u9001\u5206\u7247\u5b8c\u6210");
        this.MergeFile(finalName, uploadId, chunkCount);
    }

    private void MergeFile(String finalName, String uploadId, long chunkCount) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, XmlParserException, InvalidResponseException, InternalException {
        Part[] parts = new Part[(int)chunkCount];
        ListPartsResponse partResult = this.customMinioClient.listMultipart(this.minioProperties.getBucketsName(), null, finalName, Integer.valueOf(10000), Integer.valueOf(0), uploadId, null, null);
        int partNumber = 1;
        for (Part part : partResult.result().partList()) {
            parts[partNumber - 1] = new Part(partNumber, part.etag());
            ++partNumber;
        }
        this.customMinioClient.mergeMultipartUpload(this.minioProperties.getBucketsName(), null, finalName, uploadId, parts, null, null);
    }

    private String getUploadId(String finalName,String contentType) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, XmlParserException, InvalidResponseException, InternalException {
//        String contentType = "application/octet-stream";
        HashMultimap headers = HashMultimap.create();
        headers.put((Object)"Content-Type", (Object)contentType);
        return this.customMinioClient.initMultiPartUpload(this.minioProperties.getBucketsName(), null, finalName, (Multimap)headers, null);
    }

    private void processChunk(int chunkIndex, String finalName, MultipartFile file, long position, int readSize, String uploadId) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, XmlParserException, InvalidResponseException, InternalException {
        byte[] buffer = new byte[readSize];
        InputStream inputStream = file.getInputStream();
        inputStream.skip(position);
        int bytesRead = inputStream.read(buffer);
        String contentType = "application/octet-stream";
        HashMultimap headers = HashMultimap.create();
        headers.put((Object)"Content-Type", (Object)contentType);
        UploadPartResponse uploadPartResponse = this.customMinioClient.uploadMultiPart(this.minioProperties.getBucketsName(), null, finalName, (Object)buffer, (long)bytesRead, uploadId, chunkIndex, (Multimap)headers, null);
        System.out.println("chunk[" + chunkIndex + "] buffer size: [" + buffer.length + " Byte] upload etag: [" + uploadPartResponse.etag() + "]");
        countDownLatch.countDown();
    }

    @Generated
    public MinioUtils(CustomMinioClient customMinioClient, MinioProperties minioProperties, ThreadPoolTaskExecutor threadPool) {
        this.customMinioClient = customMinioClient;
        this.minioProperties = minioProperties;
        this.threadPool = threadPool;
    }

    @Generated
    public CustomMinioClient getCustomMinioClient() {
        return this.customMinioClient;
    }

    @Generated
    public MinioProperties getMinioProperties() {
        return this.minioProperties;
    }

    @Generated
    public ThreadPoolTaskExecutor getThreadPool() {
        return this.threadPool;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MinioUtils)) {
            return false;
        }
        MinioUtils other = (MinioUtils)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        CustomMinioClient this$customMinioClient = this.getCustomMinioClient();
        CustomMinioClient other$customMinioClient = other.getCustomMinioClient();
        if (this$customMinioClient == null ? other$customMinioClient != null : !this$customMinioClient.equals(other$customMinioClient)) {
            return false;
        }
        MinioProperties this$minioProperties = this.getMinioProperties();
        MinioProperties other$minioProperties = other.getMinioProperties();
        if (this$minioProperties == null ? other$minioProperties != null : !this$minioProperties.equals(other$minioProperties)) {
            return false;
        }
        ThreadPoolTaskExecutor this$threadPool = this.getThreadPool();
        ThreadPoolTaskExecutor other$threadPool = other.getThreadPool();
        return !(this$threadPool == null ? other$threadPool != null : !this$threadPool.equals(other$threadPool));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof MinioUtils;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        CustomMinioClient $customMinioClient = this.getCustomMinioClient();
        result = result * 59 + ($customMinioClient == null ? 43 : $customMinioClient.hashCode());
        MinioProperties $minioProperties = this.getMinioProperties();
        result = result * 59 + ($minioProperties == null ? 43 : $minioProperties.hashCode());
        ThreadPoolTaskExecutor $threadPool = this.getThreadPool();
        result = result * 59 + ($threadPool == null ? 43 : $threadPool.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "MinioUtils(customMinioClient=" + String.valueOf(this.getCustomMinioClient()) + ", minioProperties=" + String.valueOf(this.getMinioProperties()) + ", threadPool=" + String.valueOf(this.getThreadPool()) + ")";
    }
}

