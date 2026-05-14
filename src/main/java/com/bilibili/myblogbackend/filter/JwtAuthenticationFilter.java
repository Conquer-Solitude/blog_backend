/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson2.JSON
 *  com.bilibili.myblogbackend.common.Result
 *  com.bilibili.myblogbackend.dto.po.User
 *  com.bilibili.myblogbackend.dto.vo.LoginUser
 *  com.bilibili.myblogbackend.exception.NoLoginException
 *  com.bilibili.myblogbackend.filter.JwtAuthenticationFilter
 *  com.bilibili.myblogbackend.util.JWTUtils
 *  jakarta.servlet.FilterChain
 *  jakarta.servlet.ServletException
 *  jakarta.servlet.ServletOutputStream
 *  jakarta.servlet.ServletRequest
 *  jakarta.servlet.ServletResponse
 *  jakarta.servlet.http.HttpServletRequest
 *  jakarta.servlet.http.HttpServletResponse
 *  lombok.Generated
 *  org.springframework.data.redis.core.RedisTemplate
 *  org.springframework.security.authentication.UsernamePasswordAuthenticationToken
 *  org.springframework.security.core.Authentication
 *  org.springframework.security.core.context.SecurityContextHolder
 *  org.springframework.stereotype.Component
 *  org.springframework.util.AntPathMatcher
 *  org.springframework.web.filter.OncePerRequestFilter
 */
package com.bilibili.myblogbackend.filter;

import com.alibaba.fastjson2.JSON;
import com.bilibili.myblogbackend.common.Result;
import com.bilibili.myblogbackend.common.UserContent;
import com.bilibili.myblogbackend.dto.po.User;
import com.bilibili.myblogbackend.dto.vo.LoginUser;
import com.bilibili.myblogbackend.exception.NoLoginException;
import com.bilibili.myblogbackend.util.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import lombok.Generated;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter
extends OncePerRequestFilter {
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final RedisTemplate<String, Object> redisTemplate;

    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String uri = request.getRequestURI();
        if (this.pathMatcher.match("/comment/video", uri)
                || this.pathMatcher.match("/message/submit", uri)
                || this.pathMatcher.match("/treehole/add", uri)
                || this.pathMatcher.match("/file/**", uri)) {
            try {
                this.vaildToken(request);
            } catch (NoLoginException e) {
                response.setContentType("application/json;Charset=UTF-8");
                ServletOutputStream outputStream = response.getOutputStream();
                String jsonString = JSON.toJSONString(Result.success(e.getMessage()));
                outputStream.write(jsonString.getBytes(Charset.defaultCharset()));
                outputStream.flush();
                outputStream.close();
                return;
            } catch (Exception var9) {
                response.setContentType("application/json;Charset=UTF-8");
                ServletOutputStream outputStream = response.getOutputStream();
                String jsonString = JSON.toJSONString(Result.success("Token过期,请重新登录一下"));
                outputStream.write(jsonString.getBytes(Charset.defaultCharset()));
                outputStream.flush();
                outputStream.close();
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void vaildToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        Integer userId = JWTUtils.parseToken(token);
        UserContent.set(userId);
        LoginUser loginUser = new LoginUser((new User()).setId(userId));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, (Object)null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Generated
    public JwtAuthenticationFilter(final RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}

