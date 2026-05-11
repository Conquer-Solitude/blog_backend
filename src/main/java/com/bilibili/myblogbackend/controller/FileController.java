/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.common.Result
 *  com.bilibili.myblogbackend.controller.FileController
 *  com.bilibili.myblogbackend.dto.po.Article
 *  com.bilibili.myblogbackend.exception.BaseException
 *  com.bilibili.myblogbackend.service.IArticleService
 *  com.bilibili.myblogbackend.util.oss.MinioUtils
 *  lombok.Generated
 *  org.springframework.data.redis.core.RedisTemplate
 *  org.springframework.security.access.prepost.PreAuthorize
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 *  org.springframework.web.multipart.MultipartFile
 */
package com.bilibili.myblogbackend.controller;

import com.bilibili.myblogbackend.common.Result;
import com.bilibili.myblogbackend.dto.po.Article;
import com.bilibili.myblogbackend.exception.BaseException;
import com.bilibili.myblogbackend.service.IArticleService;
import com.bilibili.myblogbackend.util.oss.MinioUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Generated;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value={"/file"})
@PreAuthorize(value="hasAuthority('highest_authority')")
public class FileController {
    private final MinioUtils minioUtils;
    private final IArticleService articleService;
    private final RedisTemplate<String, Object> redisTemplate;

    @PostMapping({"/upload"})
    public Result upload(MultipartFile file) {
        String var10000 = String.valueOf(LocalDateTime.now());
        String finalName = var10000 + file.getOriginalFilename();
        (new Thread(() -> {
            try {
                this.minioUtils.uploadImage(finalName, file);
            } catch (Exception var4) {
                throw new BaseException("图片上传出错了");
            }
        })).start();
        System.out.println("正在上传图片，请稍等");
        return Result.success(this.articleService.addArticle(finalName));
    }

    @PostMapping({"/submit"})
    public Result submit(@RequestBody Article article) {
        return Result.success(this.articleService.update(article));
    }

    @PostMapping({"/video"})
    public Result video(MultipartFile file) {
        System.out.println("正在上传视频，请稍等");
        String var10000 = String.valueOf(LocalDate.now());
        String finalName = var10000 + file.getOriginalFilename();

        try {
            this.minioUtils.uploadPartFile(finalName, file);
        } catch (Exception var4) {
            throw new BaseException("视频上传失败了");
        }

        return Result.success(finalName);
    }

    @Generated
    public FileController(final MinioUtils minioUtils, final IArticleService articleService, final RedisTemplate<String, Object> redisTemplate) {
        this.minioUtils = minioUtils;
        this.articleService = articleService;
        this.redisTemplate = redisTemplate;
    }
}

