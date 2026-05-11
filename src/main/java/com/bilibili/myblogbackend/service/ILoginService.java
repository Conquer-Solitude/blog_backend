/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.dto.UserDto
 *  com.bilibili.myblogbackend.dto.vo.UserVO
 *  com.bilibili.myblogbackend.service.ILoginService
 */
package com.bilibili.myblogbackend.service;

import com.bilibili.myblogbackend.dto.UserDto;
import com.bilibili.myblogbackend.dto.vo.UserVO;

public interface ILoginService {
    public UserDto login(UserVO var1);

    public String register(UserVO var1);
}

