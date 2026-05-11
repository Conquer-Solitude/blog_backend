/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.extension.service.IService
 *  com.bilibili.myblogbackend.dto.po.TreeHole
 *  com.bilibili.myblogbackend.service.ITreeHoleService
 */
package com.bilibili.myblogbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bilibili.myblogbackend.dto.po.TreeHole;
import java.util.List;

public interface ITreeHoleService
extends IService<TreeHole> {
    public List<TreeHole> getAllTreeHole();
}

