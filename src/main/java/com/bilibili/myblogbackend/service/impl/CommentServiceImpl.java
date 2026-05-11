package com.bilibili.myblogbackend.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.myblogbackend.convertor.ClassConvertor;
import com.bilibili.myblogbackend.dto.CommentDto;
import com.bilibili.myblogbackend.dto.po.Comment;
import com.bilibili.myblogbackend.dto.po.User;
import com.bilibili.myblogbackend.dto.vo.CommentVO;
import com.bilibili.myblogbackend.mapper.CommentMapper;
import com.bilibili.myblogbackend.mapper.UserMapper;
import com.bilibili.myblogbackend.service.ICommentService;
import java.io.Serializable;
import java.util.List;
import lombok.Generated;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl
extends ServiceImpl<CommentMapper, Comment>
implements ICommentService {
    private final CommentMapper commentMapper;
    private final UserMapper userMapper;
    private final ClassConvertor classConvertor;

    public String videoComment(CommentVO commentVO) {
        return this.commentMapper.insert(this.classConvertor.toComment(commentVO)) != 0 ? "评论成功" : "评论失败";
    }

    public List<CommentDto> getCommentById(Integer id) {
        List<Comment> list = ((LambdaQueryChainWrapper)this.lambdaQuery().eq(Comment::getArticleId, id)).list();
        return list.stream().map((item) -> (new CommentDto()).setId(item.getId()).setContent(item.getContent()).setUserName(((User)this.userMapper.selectById(item.getUserId())).getEmail())).toList();
    }

    @Generated
    public CommentServiceImpl(final CommentMapper commentMapper, final UserMapper userMapper) {
        this.classConvertor = ClassConvertor.INSTANCE;
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
    }
}
