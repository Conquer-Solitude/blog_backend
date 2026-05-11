/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.convertor.ClassConvertor
 *  com.bilibili.myblogbackend.convertor.ClassConvertorImpl
 *  com.bilibili.myblogbackend.dto.UserDto
 *  com.bilibili.myblogbackend.dto.po.Article
 *  com.bilibili.myblogbackend.dto.po.Comment
 *  com.bilibili.myblogbackend.dto.po.TreeHole
 *  com.bilibili.myblogbackend.dto.po.User
 *  com.bilibili.myblogbackend.dto.vo.ArticleVO
 *  com.bilibili.myblogbackend.dto.vo.CommentVO
 *  com.bilibili.myblogbackend.dto.vo.TreeHoleVO
 *  com.bilibili.myblogbackend.dto.vo.UserVO
 */
package com.bilibili.myblogbackend.convertor;

import com.bilibili.myblogbackend.convertor.ClassConvertor;
import com.bilibili.myblogbackend.dto.UserDto;
import com.bilibili.myblogbackend.dto.po.Article;
import com.bilibili.myblogbackend.dto.po.Comment;
import com.bilibili.myblogbackend.dto.po.TreeHole;
import com.bilibili.myblogbackend.dto.po.User;
import com.bilibili.myblogbackend.dto.vo.ArticleVO;
import com.bilibili.myblogbackend.dto.vo.CommentVO;
import com.bilibili.myblogbackend.dto.vo.TreeHoleVO;
import com.bilibili.myblogbackend.dto.vo.UserVO;
import java.util.ArrayList;
import java.util.List;

public class ClassConvertorImpl
implements ClassConvertor {
    public List<ArticleVO> toArticleVO(List<Article> list) {
        if (list == null) {
            return null;
        }
        ArrayList<ArticleVO> list1 = new ArrayList<ArticleVO>(list.size());
        for (Article article : list) {
            list1.add(this.articleToArticleVO(article));
        }
        return list1;
    }

    public Comment toComment(CommentVO commentVO) {
        if (commentVO == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setUserId(commentVO.getUserId());
        comment.setArticleId(commentVO.getArticleId());
        comment.setContent(commentVO.getContent());
        return comment;
    }

    public List<TreeHoleVO> toTreeHoleVO(List<TreeHole> list) {
        if (list == null) {
            return null;
        }
        ArrayList<TreeHoleVO> list1 = new ArrayList<TreeHoleVO>(list.size());
        for (TreeHole treeHole : list) {
            list1.add(this.treeHoleToTreeHoleVO(treeHole));
        }
        return list1;
    }

    public User toUser(UserVO userVO) {
        if (userVO == null) {
            return null;
        }
        User user = new User();
        user.setId(userVO.getId());
        user.setEmail(userVO.getEmail());
        user.setIsForbidden(userVO.getIsForbidden());
        user.setPassword(userVO.getPassword());
        return user;
    }

    public UserDto toUserDto(User user, String jwt) {
        if (user == null && jwt == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        if (user != null) {
            userDto.setId(user.getId());
            userDto.setEmail(user.getEmail());
            userDto.setIsForbidden(user.getIsForbidden());
        }
        userDto.setJwt(jwt);
        return userDto;
    }

    protected ArticleVO articleToArticleVO(Article article) {
        if (article == null) {
            return null;
        }
        ArticleVO articleVO = new ArticleVO();
        articleVO.setId(article.getId());
        articleVO.setArticleUrl(article.getArticleUrl());
        articleVO.setTitle(article.getTitle());
        articleVO.setCreateTime(article.getCreateTime());
        articleVO.setIntroduce(article.getIntroduce());
        articleVO.setCover(article.getCover());
        return articleVO;
    }

    protected TreeHoleVO treeHoleToTreeHoleVO(TreeHole treeHole) {
        if (treeHole == null) {
            return null;
        }
        TreeHoleVO treeHoleVO = new TreeHoleVO();
        treeHoleVO.setId(treeHole.getId());
        treeHoleVO.setUserId(treeHole.getUserId());
        treeHoleVO.setContent(treeHole.getContent());
        return treeHoleVO;
    }
}

