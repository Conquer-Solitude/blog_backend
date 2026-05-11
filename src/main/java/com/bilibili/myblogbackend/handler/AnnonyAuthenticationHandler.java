/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson2.JSON
 *  com.bilibili.myblogbackend.common.Result
 *  com.bilibili.myblogbackend.handler.AnnonyAuthenticationHandler
 *  jakarta.servlet.ServletException
 *  jakarta.servlet.ServletOutputStream
 *  jakarta.servlet.http.HttpServletRequest
 *  jakarta.servlet.http.HttpServletResponse
 *  org.springframework.security.authentication.BadCredentialsException
 *  org.springframework.security.authentication.InternalAuthenticationServiceException
 *  org.springframework.security.core.AuthenticationException
 *  org.springframework.security.web.AuthenticationEntryPoint
 *  org.springframework.stereotype.Component
 */
package com.bilibili.myblogbackend.handler;

import com.alibaba.fastjson2.JSON;
import com.bilibili.myblogbackend.common.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AnnonyAuthenticationHandler
implements AuthenticationEntryPoint {
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;Charset=UTF-8");
        response.setStatus(401);
        ServletOutputStream outputStream = response.getOutputStream();
        String jsonString;
        if (authException instanceof BadCredentialsException) {
            jsonString = JSON.toJSONString(Result.success("密码错误"));
        } else if (authException instanceof InternalAuthenticationServiceException) {
            jsonString = JSON.toJSONString(Result.success("抱歉该用户已不存在"));
        } else {
            jsonString = JSON.toJSONString(Result.error("权限不足，请联系管理员"));
        }

        outputStream.write(jsonString.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}

