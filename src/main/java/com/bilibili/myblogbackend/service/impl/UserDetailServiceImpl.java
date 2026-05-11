/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
 *  com.bilibili.myblogbackend.dto.po.User
 *  com.bilibili.myblogbackend.dto.vo.LoginUser
 *  com.bilibili.myblogbackend.service.impl.UserDetailServiceImpl
 *  com.bilibili.myblogbackend.service.impl.UserServiceImpl
 *  lombok.Generated
 *  org.springframework.security.authentication.InternalAuthenticationServiceException
 *  org.springframework.security.core.userdetails.UserDetails
 *  org.springframework.security.core.userdetails.UserDetailsService
 *  org.springframework.security.core.userdetails.UsernameNotFoundException
 *  org.springframework.stereotype.Service
 */
package com.bilibili.myblogbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bilibili.myblogbackend.dto.po.User;
import com.bilibili.myblogbackend.dto.vo.LoginUser;
import com.bilibili.myblogbackend.service.impl.UserServiceImpl;
import java.util.Objects;
import lombok.Generated;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl
implements UserDetailsService {
    private final UserServiceImpl userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User)this.userService.getOne((Wrapper)(new QueryWrapper()).eq("email", username));
        if (Objects.isNull(user)) {
            throw new InternalAuthenticationServiceException("用户名不存在");
        } else {
            return new LoginUser(user);
        }
    }

    @Generated
    public UserDetailServiceImpl(final UserServiceImpl userService) {
        this.userService = userService;
    }
}
