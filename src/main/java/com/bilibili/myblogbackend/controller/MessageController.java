/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.common.Result
 *  com.bilibili.myblogbackend.controller.MessageController
 *  com.bilibili.myblogbackend.dto.po.Message
 *  com.bilibili.myblogbackend.service.IMessageService
 *  lombok.Generated
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.bilibili.myblogbackend.controller;

import com.bilibili.myblogbackend.common.Result;
import com.bilibili.myblogbackend.dto.po.Message;
import com.bilibili.myblogbackend.service.IMessageService;
import lombok.Generated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/message"})
public class MessageController {
    private final IMessageService messageService;

    @GetMapping
    public Result allMessage() {
        return Result.success((Object)this.messageService.getAllMesage());
    }

    @PostMapping(value={"/submit"})
    public Result submitMessage(@RequestBody Message message) {
        return Result.success((Object)this.messageService.save((Message) message));
    }

    @Generated
    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }
}

