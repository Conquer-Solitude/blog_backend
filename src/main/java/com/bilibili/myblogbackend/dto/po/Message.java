/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotation.IdType
 *  com.baomidou.mybatisplus.annotation.TableId
 *  com.baomidou.mybatisplus.annotation.TableName
 *  com.bilibili.myblogbackend.dto.po.Message
 *  lombok.Generated
 */
package com.bilibili.myblogbackend.dto.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Generated;

@TableName(value="message")
public class Message {
    private static final long serialVersionUID = 1L;
    @TableId(value="id", type=IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String content;
    private Integer isDelete;

    @Generated
    public Integer getId() {
        return this.id;
    }

    @Generated
    public Integer getUserId() {
        return this.userId;
    }

    @Generated
    public String getContent() {
        return this.content;
    }

    @Generated
    public Integer getIsDelete() {
        return this.isDelete;
    }

    @Generated
    public Message setId(Integer id) {
        this.id = id;
        return this;
    }

    @Generated
    public Message setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @Generated
    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    @Generated
    public Message setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Generated
    public String toString() {
        return "Message(id=" + this.getId() + ", userId=" + this.getUserId() + ", content=" + this.getContent() + ", isDelete=" + this.getIsDelete() + ")";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Message)) {
            return false;
        }
        Message other = (Message)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$id = this.getId();
        Integer other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Integer this$userId = this.getUserId();
        Integer other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !((Object)this$userId).equals(other$userId)) {
            return false;
        }
        Integer this$isDelete = this.getIsDelete();
        Integer other$isDelete = other.getIsDelete();
        if (this$isDelete == null ? other$isDelete != null : !((Object)this$isDelete).equals(other$isDelete)) {
            return false;
        }
        String this$content = this.getContent();
        String other$content = other.getContent();
        return !(this$content == null ? other$content != null : !this$content.equals(other$content));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Message;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Integer $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Integer $isDelete = this.getIsDelete();
        result = result * 59 + ($isDelete == null ? 43 : ((Object)$isDelete).hashCode());
        String $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        return result;
    }

    @Generated
    public Message(Integer id, Integer userId, String content, Integer isDelete) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.isDelete = isDelete;
    }
}

