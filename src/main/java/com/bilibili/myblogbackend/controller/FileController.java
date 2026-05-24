package com.bilibili.myblogbackend.controller;

import com.bilibili.myblogbackend.common.Result;
import com.bilibili.myblogbackend.dto.po.Article;
import com.bilibili.myblogbackend.exception.BaseException;
import com.bilibili.myblogbackend.service.IArticleService;
import com.bilibili.myblogbackend.util.oss.MinioUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping(value = {"/file"})
@PreAuthorize(value = "hasAuthority('highest_authority')")
@RequiredArgsConstructor
public class FileController {

    private static final long MAX_IMAGE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final long MAX_VIDEO_SIZE = 500 * 1024 * 1024; // 500MB
    private static final List<String> ALLOWED_IMAGE_TYPES = Arrays.asList(
            "image/jpeg", "image/png", "image/gif", "image/webp", "image/jpg"
    );
    private static final List<String> ALLOWED_VIDEO_TYPES = Arrays.asList(
            "video/mp4", "video/avi", "video/mov", "video/wmv", "video/flv", "video/mkv"
    );

    private final MinioUtils minioUtils;
    private final IArticleService articleService;
    private final RedisTemplate<String, Object> redisTemplate;

    @PostMapping({"/upload"})
    public Result upload(MultipartFile file) {
        long requestStartTime = System.currentTimeMillis();
        String originalFilename = file.getOriginalFilename();
        long fileSize = file.getSize();
        String contentType = file.getContentType();

        log.info("[文件上传] 收到图片上传请求 | 文件名: {} | 大小: {} bytes | ContentType: {}",
                originalFilename, fileSize, contentType);

        // 参数校验
        if (file.isEmpty()) {
            log.warn("[文件上传] 上传失败 | 原因: 文件为空");
            throw new BaseException("上传文件不能为空");
        }

        if (fileSize > MAX_IMAGE_SIZE) {
            log.warn("[文件上传] 上传失败 | 文件名: {} | 大小: {} bytes | 原因: 超过最大限制 {} bytes",
                    originalFilename, fileSize, MAX_IMAGE_SIZE);
            throw new BaseException("图片大小不能超过 10MB");
        }

        if (contentType == null || !ALLOWED_IMAGE_TYPES.contains(contentType.toLowerCase())) {
            log.warn("[文件上传] 上传失败 | 文件名: {} | ContentType: {} | 原因: 不支持的文件类型",
                    originalFilename, contentType);
            throw new BaseException("仅支持 JPG、JPEG、PNG、GIF、WEBP 格式的图片");
        }

        String finalName = LocalDateTime.now().toString().replace(":", "-") + "_" + originalFilename;
        log.info("[文件上传] 开始处理 | 生成文件名: {} | 原始文件名: {}", finalName, originalFilename);

        try {
            // 异步上传图片
            CompletableFuture.runAsync(() -> {
                long asyncStartTime = System.currentTimeMillis();
                try {
                    minioUtils.uploadImage(finalName, file);
                    long asyncDuration = System.currentTimeMillis() - asyncStartTime;
                    log.info("[文件上传] 异步上传完成 | 文件名: {} | 异步耗时: {} ms", finalName, asyncDuration);
                } catch (Exception e) {
                    long asyncDuration = System.currentTimeMillis() - asyncStartTime;
                    log.error("[文件上传] 异步上传失败 | 文件名: {} | 异步耗时: {} ms | 错误: {}",
                            finalName, asyncDuration, e.getMessage(), e);
                    throw new BaseException("图片上传出错了: " + e.getMessage());
                }
            });

            log.info("[文件上传] 异步任务已提交 | 文件名: {}", finalName);

            // 同步保存到数据库
            long dbStartTime = System.currentTimeMillis();
            Integer result = articleService.addArticle(finalName);
            long dbDuration = System.currentTimeMillis() - dbStartTime;

            long totalDuration = System.currentTimeMillis() - requestStartTime;
            log.info("[文件上传] 请求处理完成 | 文件名: {} | 数据库耗时: {} ms | 总耗时: {} ms",
                    finalName, dbDuration, totalDuration);

            return Result.success(result);

        } catch (Exception e) {
            long totalDuration = System.currentTimeMillis() - requestStartTime;
            log.error("[文件上传] 请求处理失败 | 文件名: {} | 总耗时: {} ms | 错误: {}",
                    finalName, totalDuration, e.getMessage(), e);
            throw new BaseException("图片上传出错了: " + e.getMessage());
        }
    }

    @PostMapping({"/submit"})
    public Result submit(@RequestBody Article article) {
        log.info("[文章提交] 收到提交请求 | 文章ID: {} | 标题: {}", article.getId(), article.getTitle());
        try {
            String result = articleService.update(article);
            log.info("[文章提交] 提交成功 | 文章ID: {}", article.getId());
            return Result.success(result);
        } catch (Exception e) {
            log.error("[文章提交] 提交失败 | 文章ID: {} | 错误: {}", article.getId(), e.getMessage(), e);
            throw new BaseException("文章提交失败: " + e.getMessage());
        }
    }

    @PostMapping({"/video"})
    public Result video(MultipartFile file) {
        long requestStartTime = System.currentTimeMillis();
        String originalFilename = file.getOriginalFilename();
        long fileSize = file.getSize();
        String contentType = file.getContentType();

        log.info("[视频上传] 收到视频上传请求 | 文件名: {} | 大小: {} bytes ({:.2f} MB) | ContentType: {}",
                originalFilename, fileSize, fileSize / (1024.0 * 1024.0), contentType);

        // 参数校验
        if (file.isEmpty()) {
            log.warn("[视频上传] 上传失败 | 原因: 文件为空");
            throw new BaseException("上传文件不能为空");
        }

        if (fileSize > MAX_VIDEO_SIZE) {
            log.warn("[视频上传] 上传失败 | 文件名: {} | 大小: {} bytes | 原因: 超过最大限制 {} bytes",
                    originalFilename, fileSize, MAX_VIDEO_SIZE);
            throw new BaseException("视频大小不能超过 500MB");
        }

        if (contentType == null || !ALLOWED_VIDEO_TYPES.contains(contentType.toLowerCase())) {
            log.warn("[视频上传] 上传失败 | 文件名: {} | ContentType: {} | 原因: 不支持的文件类型",
                    originalFilename, contentType);
            throw new BaseException("仅支持 MP4、AVI、MOV、WMV、FLV、MKV 格式的视频");
        }

        String finalName = LocalDate.now().toString() + "_" + originalFilename;
        log.info("[视频上传] 开始处理 | 生成文件名: {} | 原始文件名: {}", finalName, originalFilename);

        try {
            minioUtils.uploadPartFile(finalName, file);

            long totalDuration = System.currentTimeMillis() - requestStartTime;
            log.info("[视频上传] 上传成功 | 文件名: {} | 总耗时: {} ms", finalName, totalDuration);

            return Result.success(finalName);

        } catch (Exception e) {
            long totalDuration = System.currentTimeMillis() - requestStartTime;
            log.error("[视频上传] 上传失败 | 文件名: {} | 总耗时: {} ms | 错误: {}",
                    finalName, totalDuration, e.getMessage(), e);
            throw new BaseException("视频上传失败了: " + e.getMessage());
        }
    }
}
