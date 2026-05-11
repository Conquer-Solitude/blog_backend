/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.dto.CommentDto
 *  lombok.Generated
 */
package com.bilibili.myblogbackend.dto;

import java.io.Serializable;
import lombok.Generated;

public class CommentDto
implements Serializable {
    private static final long serialVersionUID = -8066575833809972726L;
    private Integer id;
    private String userName;
    private String content;

    @Generated
    public CommentDto() {
    }

    @Generated
    public Integer getId() {
        return this.id;
    }

    @Generated
    public String getUserName() {
        return this.userName;
    }

    @Generated
    public String getContent() {
        return this.content;
    }

    @Generated
    public CommentDto setId(Integer id) {
        this.id = id;
        return this;
    }

    @Generated
    public CommentDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @Generated
    public CommentDto setContent(String content) {
        this.content = content;
        return this;
    }

    @Generated
    public String toString() {
        return "CommentDto(id=" + this.getId() + ", userName=" + this.getUserName() + ", content=" + this.getContent() + ")";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CommentDto)) {
            return false;
        }
        CommentDto other = (CommentDto)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$id = this.getId();
        Integer other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$userName = this.getUserName();
        String other$userName = other.getUserName();
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) {
            return false;
        }
        String this$content = this.getContent();
        String other$content = other.getContent();
        return !(this$content == null ? other$content != null : !this$content.equals(other$content));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof CommentDto;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $userName = this.getUserName();
        result = result * 59 + ($userName == null ? 43 : $userName.hashCode());
        String $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        return result;
    }
}

