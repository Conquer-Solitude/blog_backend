/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.dto.vo.TreeHoleVO
 *  lombok.Generated
 */
package com.bilibili.myblogbackend.dto.vo;

import java.io.Serializable;
import lombok.Generated;

public class TreeHoleVO
implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private String content;

    @Generated
    public TreeHoleVO() {
    }

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
    public TreeHoleVO setId(Integer id) {
        this.id = id;
        return this;
    }

    @Generated
    public TreeHoleVO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @Generated
    public TreeHoleVO setContent(String content) {
        this.content = content;
        return this;
    }

    @Generated
    public String toString() {
        return "TreeHoleVO(id=" + this.getId() + ", userId=" + this.getUserId() + ", content=" + this.getContent() + ")";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TreeHoleVO)) {
            return false;
        }
        TreeHoleVO other = (TreeHoleVO)o;
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
        String this$content = this.getContent();
        String other$content = other.getContent();
        return !(this$content == null ? other$content != null : !this$content.equals(other$content));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof TreeHoleVO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Integer $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        String $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        return result;
    }
}

