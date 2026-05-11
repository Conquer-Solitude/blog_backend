/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.dto.CustomAuthtication
 *  org.springframework.security.core.GrantedAuthority
 */
package com.bilibili.myblogbackend.dto;

import org.springframework.security.core.GrantedAuthority;

public class CustomAuthtication
implements GrantedAuthority {
    public String getAuthority() {
        return "highest_authority";
    }
}

