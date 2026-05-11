/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.common.UserContent
 */
package com.bilibili.myblogbackend.common;

public class UserContent {
    public static final ThreadLocal<Integer> tl = new ThreadLocal();

    public static void set(Integer id) {
        tl.set(id);
    }

    public static Integer get() {
        return (Integer)tl.get();
    }

    public static void remove() {
        tl.remove();
    }
}

