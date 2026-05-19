/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.common.Result
 *  com.bilibili.myblogbackend.controller.LoginController
 *  com.bilibili.myblogbackend.dto.vo.UserVO
 *  com.bilibili.myblogbackend.exception.BaseException
 *  com.bilibili.myblogbackend.service.ILoginService
 *  com.bilibili.myblogbackend.util.EmailUtils
 *  jakarta.mail.MessagingException
 *  jakarta.servlet.http.HttpServletRequest
 *  lombok.Generated
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RestController
 */
package com.bilibili.myblogbackend.controller;

import com.bilibili.myblogbackend.common.Result;
import com.bilibili.myblogbackend.dto.vo.UserVO;
import com.bilibili.myblogbackend.exception.BaseException;
import com.bilibili.myblogbackend.service.ILoginService;
import com.bilibili.myblogbackend.util.EmailUtils;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import lombok.Generated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final ILoginService loginService;
    private final EmailUtils emailUtils;

    @PostMapping({"/login"})
    public Result login(@RequestBody UserVO user) {
        return Result.success(this.loginService.login(user));
    }

    @PostMapping({"/send"})
    public Result sendEmail(@RequestBody UserVO registerUser, HttpServletRequest request) {
        try {
            System.out.println("发送邮件");
            String code = this.emailUtils.sendMessage(registerUser.getEmail());
            request.getSession().setAttribute(registerUser.getEmail(), code);
            System.out.println("发送邮件成功");
        } catch (UnsupportedEncodingException | MessagingException var4) {
            throw new BaseException("邮件发送失败，请注意QQ邮件格式");
        }

        return Result.success("邮件已发送请注意查收");
    }

    @PostMapping({"/register"})
    public Result register(@RequestBody UserVO userVO, HttpServletRequest request) {
        if (userVO.getCode().equals(request.getSession().getAttribute(userVO.getEmail()))) {
            String register = this.loginService.register(userVO);
            request.getSession().removeAttribute(userVO.getEmail());
            return Result.success(register);
        } else {
            return Result.success("验证码错误");
        }
    }
    @GetMapping({"/istotest"})
    public Result logout() {
        return Result.success("测试成功");
    }

    @Generated
    public LoginController(final ILoginService loginService, final EmailUtils emailUtils) {
        this.loginService = loginService;
        this.emailUtils = emailUtils;
    }
}

