/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 *  com.bilibili.myblogbackend.dto.po.TreeHole
 *  com.bilibili.myblogbackend.mapper.TreeHoleMapper
 *  com.bilibili.myblogbackend.service.ITreeHoleService
 *  com.bilibili.myblogbackend.service.impl.TreeHoleServiceImpl
 *  org.springframework.stereotype.Service
 */
package com.bilibili.myblogbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.myblogbackend.dto.po.TreeHole;
import com.bilibili.myblogbackend.mapper.TreeHoleMapper;
import com.bilibili.myblogbackend.service.ITreeHoleService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TreeHoleServiceImpl
extends ServiceImpl<TreeHoleMapper, TreeHole>
implements ITreeHoleService {
    public List<TreeHole> getAllTreeHole() {
        return null;
    }
}

