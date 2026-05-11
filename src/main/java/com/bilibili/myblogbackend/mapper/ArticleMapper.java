/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.mapper.BaseMapper
 *  com.bilibili.myblogbackend.dto.po.Article
 *  com.bilibili.myblogbackend.mapper.ArticleMapper
 *  org.apache.ibatis.annotations.Mapper
 */
package com.bilibili.myblogbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bilibili.myblogbackend.dto.po.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper
extends BaseMapper<Article> {
    public void updateArticle(Article var1);
}

