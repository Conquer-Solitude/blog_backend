/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.common.Result
 *  com.bilibili.myblogbackend.controller.ArticleController
 *  com.bilibili.myblogbackend.service.IArticleService
 *  lombok.Generated
 *  org.springframework.data.redis.core.RedisTemplate
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.bilibili.myblogbackend.controller;

import com.bilibili.myblogbackend.common.Result;
import com.bilibili.myblogbackend.service.IArticleService;
import lombok.Generated;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/article"})
public class ArticleController {
    private final IArticleService articleService;
    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping(value={"/latest"})
    public Result latestArticle() {
        return Result.success((Object)this.articleService.getLatestArticle());
    }

    @GetMapping(value={"/all"})
    public Result allArticle() {
        return Result.success((Object)this.articleService.getAllArticle());
    }

    @GetMapping(value={"/nums"})
    public Result articleNums() {
        return Result.success((Object)this.articleService.count());
    }

    @GetMapping(value={"/type/{id}"})
    public Result articleType(@PathVariable(value="id") Integer id) {
        return Result.success((Object)this.articleService.getArticleByType(id));
    }

    @Generated
    public ArticleController(IArticleService articleService, RedisTemplate<String, Object> redisTemplate) {
        this.articleService = articleService;
        this.redisTemplate = redisTemplate;
    }
}

