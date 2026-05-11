/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.common.Result
 *  com.bilibili.myblogbackend.controller.TestController
 *  com.bilibili.myblogbackend.exception.BaseException
 *  com.bilibili.myblogbackend.util.oss.MinioUtils
 *  lombok.Generated
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 *  org.springframework.web.multipart.MultipartFile
 */
package com.bilibili.myblogbackend.controller;

import com.bilibili.myblogbackend.common.Result;
import com.bilibili.myblogbackend.exception.BaseException;
import com.bilibili.myblogbackend.util.oss.MinioUtils;
import java.time.LocalDate;
import lombok.Generated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value={"/test"})
public class TestController {
    private final MinioUtils minioUtils;

    @PostMapping
    public Result test(MultipartFile file) throws Exception {
        long start = System.currentTimeMillis();
        String var10000 = String.valueOf(LocalDate.now());
        String finalName = var10000 + file.getOriginalFilename();

        try {
            this.minioUtils.uploadPartFile(finalName, file);
        } catch (Exception var7) {
            throw new BaseException("视频上传失败了");
        }

        long end = System.currentTimeMillis();
        return Result.success(end - start);
    }

    @Generated
    public TestController(final MinioUtils minioUtils) {
        this.minioUtils = minioUtils;
    }
}

