/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.exception.CustomAuthenticationException
 *  org.springframework.security.core.AuthenticationException
 */
package com.bilibili.myblogbackend.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException
extends AuthenticationException {
    public CustomAuthenticationException(String msg) {
        super(msg);
    }
}

