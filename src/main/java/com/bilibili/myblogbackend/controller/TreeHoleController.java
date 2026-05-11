/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.common.Result
 *  com.bilibili.myblogbackend.controller.TreeHoleController
 *  com.bilibili.myblogbackend.convertor.ClassConvertor
 *  com.bilibili.myblogbackend.dto.po.TreeHole
 *  com.bilibili.myblogbackend.service.ITreeHoleService
 *  lombok.Generated
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.bilibili.myblogbackend.controller;

import com.bilibili.myblogbackend.common.Result;
import com.bilibili.myblogbackend.convertor.ClassConvertor;
import com.bilibili.myblogbackend.dto.po.TreeHole;
import com.bilibili.myblogbackend.service.ITreeHoleService;
import lombok.Generated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/treehole"})
public class TreeHoleController {
    private final ITreeHoleService treeHoleService;
    private final ClassConvertor classConvertor;

    @GetMapping({"/all"})
    public Result allTreeHole() {
        return Result.success(this.classConvertor.toTreeHoleVO(this.treeHoleService.list()));
    }

    @PostMapping({"/add"})
    public Result addTreeHole(@RequestBody TreeHole treeHole) {
        try {
            this.treeHoleService.save(treeHole);
            return Result.success("感谢你曾来过");
        } catch (Exception var3) {
            return Result.success("抱歉该用户已不存在");
        }
    }

    @Generated
    public TreeHoleController(final ITreeHoleService treeHoleService) {
        this.classConvertor = ClassConvertor.INSTANCE;
        this.treeHoleService = treeHoleService;
    }
}

