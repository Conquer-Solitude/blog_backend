/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper
 *  com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 *  com.bilibili.myblogbackend.convertor.ClassConvertor
 *  com.bilibili.myblogbackend.dto.po.Article
 *  com.bilibili.myblogbackend.dto.vo.ArticleVO
 *  com.bilibili.myblogbackend.mapper.ArticleMapper
 *  com.bilibili.myblogbackend.service.IArticleService
 *  com.bilibili.myblogbackend.service.impl.ArticleServiceImpl
 *  lombok.Generated
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.bilibili.myblogbackend.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.myblogbackend.convertor.ClassConvertor;
import com.bilibili.myblogbackend.dto.po.Article;
import com.bilibili.myblogbackend.dto.vo.ArticleVO;
import com.bilibili.myblogbackend.mapper.ArticleMapper;
import com.bilibili.myblogbackend.service.IArticleService;
import java.time.LocalDate;
import java.util.List;
import lombok.Generated;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleServiceImpl
extends ServiceImpl<ArticleMapper, Article>
implements IArticleService {
    private final ArticleMapper articleMapper;
    private final ClassConvertor classConvertor;

    public String test() {
        return "测试成功";
    }

    public List<ArticleVO> getLatestArticle() {
        return this.classConvertor.toArticleVO(this.getAll().subList(0, 2));
    }

    public List<ArticleVO> getAllArticle() {
        return this.classConvertor.toArticleVO(this.getAll());
    }

    public List<ArticleVO> getArticleByType(Integer id) {
        return this.classConvertor.toArticleVO(((LambdaQueryChainWrapper)this.lambdaQuery().eq(Article::getType, id)).list());
    }

    @Transactional
    public Integer addArticle(String fileName) {
        Article build = Article.builder().cover(fileName).articleUrl("").createTime(LocalDate.now()).type(0).title("").introduce("").build();
        this.save(build);
        return build.getId();
    }

    public String update(Article article) {
        this.articleMapper.updateArticle(article);
        return "修改成功";
    }

    private List<Article> getAll() {
        return ((LambdaQueryChainWrapper)this.lambdaQuery().orderByDesc(Article::getCreateTime)).list();
    }

    @Generated
    public ArticleServiceImpl(final ArticleMapper articleMapper) {
        this.classConvertor = ClassConvertor.INSTANCE;
        this.articleMapper = articleMapper;
    }
}

