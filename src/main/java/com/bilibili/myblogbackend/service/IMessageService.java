/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.extension.service.IService
 *  com.bilibili.myblogbackend.dto.po.Message
 *  com.bilibili.myblogbackend.dto.vo.MessageVO
 *  com.bilibili.myblogbackend.service.IMessageService
 */
package com.bilibili.myblogbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bilibili.myblogbackend.dto.po.Message;
import com.bilibili.myblogbackend.dto.vo.MessageVO;
import java.util.List;

public interface IMessageService
extends IService<Message> {
    public List<MessageVO> getAllMesage();
}

