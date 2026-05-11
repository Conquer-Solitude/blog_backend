/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 *  com.bilibili.myblogbackend.dto.po.Message
 *  com.bilibili.myblogbackend.dto.po.User
 *  com.bilibili.myblogbackend.dto.vo.MessageVO
 *  com.bilibili.myblogbackend.mapper.MessageMapper
 *  com.bilibili.myblogbackend.mapper.UserMapper
 *  com.bilibili.myblogbackend.service.IMessageService
 *  com.bilibili.myblogbackend.service.impl.MessageService
 *  lombok.Generated
 *  org.springframework.stereotype.Service
 */
package com.bilibili.myblogbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.myblogbackend.dto.po.Message;
import com.bilibili.myblogbackend.dto.po.User;
import com.bilibili.myblogbackend.dto.vo.MessageVO;
import com.bilibili.myblogbackend.mapper.MessageMapper;
import com.bilibili.myblogbackend.mapper.UserMapper;
import com.bilibili.myblogbackend.service.IMessageService;
import java.io.Serializable;
import java.util.List;
import lombok.Generated;
import org.springframework.stereotype.Service;

@Service
public class MessageService
extends ServiceImpl<MessageMapper, Message>
implements IMessageService {
    private final UserMapper userMapper;

    public List<MessageVO> getAllMesage() {
        return this.toConvertor(this.lambdaQuery().list());
    }

    public List<MessageVO> toConvertor(List<Message> list) {
        return list.stream().map(item -> new MessageVO().setId(item.getId()).setUserName(((User)this.userMapper.selectById((Serializable)item.getUserId())).getEmail()).setContent(item.getContent())).toList();
    }

    @Generated
    public MessageService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}

