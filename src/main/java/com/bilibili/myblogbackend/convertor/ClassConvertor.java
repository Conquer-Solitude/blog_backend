package com.bilibili.myblogbackend.convertor;

import com.bilibili.myblogbackend.dto.UserDto;
import com.bilibili.myblogbackend.dto.po.Article;
import com.bilibili.myblogbackend.dto.po.Comment;
import com.bilibili.myblogbackend.dto.po.TreeHole;
import com.bilibili.myblogbackend.dto.po.User;
import com.bilibili.myblogbackend.dto.vo.ArticleVO;
import com.bilibili.myblogbackend.dto.vo.CommentVO;
import com.bilibili.myblogbackend.dto.vo.TreeHoleVO;
import com.bilibili.myblogbackend.dto.vo.UserVO;
import java.util.List;

public interface ClassConvertor {
    public static final ClassConvertor INSTANCE = new ClassConvertorImpl();

    public List<ArticleVO> toArticleVO(List<Article> articles);

    public Comment toComment(CommentVO commentVO);

    public List<TreeHoleVO> toTreeHoleVO(List<TreeHole> treeHoles);

    public User toUser(UserVO userVO);

    public UserDto toUserDto(User user, String jwt);
}
