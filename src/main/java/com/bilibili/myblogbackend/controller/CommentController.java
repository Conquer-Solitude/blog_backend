/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.common.Result
 *  com.bilibili.myblogbackend.controller.CommentController
 *  com.bilibili.myblogbackend.dto.vo.CommentVO
 *  com.bilibili.myblogbackend.service.ICommentService
 *  lombok.Generated
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.bilibili.myblogbackend.controller;

import com.bilibili.myblogbackend.common.Result;
import com.bilibili.myblogbackend.dto.vo.CommentVO;
import com.bilibili.myblogbackend.service.ICommentService;
import lombok.Generated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/comment"})
public class CommentController {
    private final ICommentService commentService;

    @PostMapping(value={"/video"})
    public Result videoComment(@RequestBody CommentVO commentVO) {
        return Result.success((Object)this.commentService.videoComment(commentVO));
    }

    @GetMapping(value={"/num"})
    public Result commentNums() {
        return Result.success((Object)this.commentService.count());
    }

    @GetMapping(value={"/{id}"})
    public Result getCommentById(@PathVariable Integer id) {
        return Result.success((Object)this.commentService.getCommentById(id));
    }

    @Generated
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }
}

