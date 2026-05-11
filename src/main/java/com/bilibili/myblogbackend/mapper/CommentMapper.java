/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.mapper.BaseMapper
 *  com.bilibili.myblogbackend.dto.po.Comment
 *  com.bilibili.myblogbackend.mapper.CommentMapper
 *  org.apache.ibatis.annotations.Mapper
 */
package com.bilibili.myblogbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bilibili.myblogbackend.dto.po.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper
extends BaseMapper<Comment> {
}

