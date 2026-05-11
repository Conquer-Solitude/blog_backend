/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotation.IdType
 *  com.baomidou.mybatisplus.annotation.TableId
 *  com.baomidou.mybatisplus.annotation.TableName
 *  com.bilibili.myblogbackend.dto.po.Comment
 *  lombok.Generated
 */
package com.bilibili.myblogbackend.dto.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Generated;

@TableName(value="comment")
public class Comment
implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value="id", type=IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer articleId;
    private String content;
    private Integer isDelete;

    @Generated
    public Comment() {
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
    public Integer getArticleId() {
        return this.articleId;
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
    public Comment setId(Integer id) {
        this.id = id;
        return this;
    }

    @Generated
    public Comment setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @Generated
    public Comment setArticleId(Integer articleId) {
        this.articleId = articleId;
        return this;
    }

    @Generated
    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    @Generated
    public Comment setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Generated
    public String toString() {
        return "Comment(id=" + this.getId() + ", userId=" + this.getUserId() + ", articleId=" + this.getArticleId() + ", content=" + this.getContent() + ", isDelete=" + this.getIsDelete() + ")";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment other = (Comment)o;
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
        Integer this$articleId = this.getArticleId();
        Integer other$articleId = other.getArticleId();
        if (this$articleId == null ? other$articleId != null : !((Object)this$articleId).equals(other$articleId)) {
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
        return other instanceof Comment;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Integer $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Integer $articleId = this.getArticleId();
        result = result * 59 + ($articleId == null ? 43 : ((Object)$articleId).hashCode());
        Integer $isDelete = this.getIsDelete();
        result = result * 59 + ($isDelete == null ? 43 : ((Object)$isDelete).hashCode());
        String $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        return result;
    }
}

