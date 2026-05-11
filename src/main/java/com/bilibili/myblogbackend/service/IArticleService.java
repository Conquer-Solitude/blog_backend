/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.extension.service.IService
 *  com.bilibili.myblogbackend.dto.po.Article
 *  com.bilibili.myblogbackend.dto.vo.ArticleVO
 *  com.bilibili.myblogbackend.service.IArticleService
 */
package com.bilibili.myblogbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bilibili.myblogbackend.dto.po.Article;
import com.bilibili.myblogbackend.dto.vo.ArticleVO;
import java.util.List;

public interface IArticleService
extends IService<Article> {
    public String test();

    public List<ArticleVO> getLatestArticle();

    public List<ArticleVO> getAllArticle();

    public List<ArticleVO> getArticleByType(Integer var1);

    public Integer addArticle(String var1);

    public String update(Article var1);
}

