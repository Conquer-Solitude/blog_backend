/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 *  com.bilibili.myblogbackend.dto.po.User
 *  com.bilibili.myblogbackend.mapper.UserMapper
 *  com.bilibili.myblogbackend.service.IUserService
 *  com.bilibili.myblogbackend.service.impl.UserServiceImpl
 *  org.springframework.stereotype.Service
 */
package com.bilibili.myblogbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.myblogbackend.dto.po.User;
import com.bilibili.myblogbackend.mapper.UserMapper;
import com.bilibili.myblogbackend.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
extends ServiceImpl<UserMapper, User>
implements IUserService {
}

