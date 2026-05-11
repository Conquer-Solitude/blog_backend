/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.common.Result
 *  com.bilibili.myblogbackend.exception.BaseException
 *  com.bilibili.myblogbackend.exception.GlobalExceptionHandler
 *  org.springframework.web.bind.annotation.ExceptionHandler
 *  org.springframework.web.bind.annotation.RestControllerAdvice
 */
package com.bilibili.myblogbackend.exception;

import com.bilibili.myblogbackend.common.Result;
import com.bilibili.myblogbackend.exception.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleMessagingException(BaseException e) {
        return Result.error((String)e.getMessage());
    }
}

