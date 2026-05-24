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
import com.bilibili.myblogbackend.service.ManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value={"/background"})
@RequiredArgsConstructor
public class BackgroundController {

    private final ManageService manageService;

    @GetMapping("/all")
    public Result getBackground(){
        return Result.success(manageService.getAllBackground());
    }

    @PostMapping("/upload")
    public Result uploadBackground(MultipartFile file) {
        return Result.success(manageService.uploadBackground(file));
    }
}

