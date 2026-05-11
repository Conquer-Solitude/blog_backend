/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.extension.service.IService
 *  com.bilibili.myblogbackend.dto.CommentDto
 *  com.bilibili.myblogbackend.dto.po.Comment
 *  com.bilibili.myblogbackend.dto.vo.CommentVO
 *  com.bilibili.myblogbackend.service.ICommentService
 */
package com.bilibili.myblogbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bilibili.myblogbackend.dto.CommentDto;
import com.bilibili.myblogbackend.dto.po.Comment;
import com.bilibili.myblogbackend.dto.vo.CommentVO;
import java.util.List;

public interface ICommentService
extends IService<Comment> {
    public String videoComment(CommentVO var1);

    public List<CommentDto> getCommentById(Integer var1);
}

