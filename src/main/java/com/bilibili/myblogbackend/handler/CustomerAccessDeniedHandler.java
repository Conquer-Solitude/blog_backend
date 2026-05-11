/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson2.JSON
 *  com.bilibili.myblogbackend.common.Result
 *  com.bilibili.myblogbackend.handler.CustomerAccessDeniedHandler
 *  jakarta.servlet.ServletException
 *  jakarta.servlet.ServletOutputStream
 *  jakarta.servlet.http.HttpServletRequest
 *  jakarta.servlet.http.HttpServletResponse
 *  org.springframework.security.access.AccessDeniedException
 *  org.springframework.security.web.access.AccessDeniedHandler
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
import java.nio.charset.Charset;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomerAccessDeniedHandler
implements AccessDeniedHandler {
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;Charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String jsonString = JSON.toJSONString(Result.success("权限不足，请联系管理员"));
        outputStream.write(jsonString.getBytes(Charset.defaultCharset()));
        outputStream.flush();
        outputStream.close();
    }
}

