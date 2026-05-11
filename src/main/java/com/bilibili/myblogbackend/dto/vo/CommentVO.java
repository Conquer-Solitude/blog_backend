
package com.bilibili.myblogbackend.dto.vo;

import java.io.Serializable;
import lombok.Generated;

public class CommentVO
implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private Integer articleId;
    private String content;

    @Generated
    public CommentVO() {
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
    public CommentVO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @Generated
    public CommentVO setArticleId(Integer articleId) {
        this.articleId = articleId;
        return this;
    }

    @Generated
    public CommentVO setContent(String content) {
        this.content = content;
        return this;
    }

    @Generated
    public String toString() {
        return "CommentVO(userId=" + this.getUserId() + ", articleId=" + this.getArticleId() + ", content=" + this.getContent() + ")";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CommentVO)) {
            return false;
        }
        CommentVO other = (CommentVO)o;
        if (!other.canEqual((Object)this)) {
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
        String this$content = this.getContent();
        String other$content = other.getContent();
        return !(this$content == null ? other$content != null : !this$content.equals(other$content));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof CommentVO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Integer $articleId = this.getArticleId();
        result = result * 59 + ($articleId == null ? 43 : ((Object)$articleId).hashCode());
        String $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        return result;
    }
}

