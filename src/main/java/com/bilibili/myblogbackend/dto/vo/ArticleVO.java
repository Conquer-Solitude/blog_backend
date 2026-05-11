
package com.bilibili.myblogbackend.dto.vo;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Generated;

public class ArticleVO
implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String articleUrl;
    private String title;
    private LocalDate createTime;
    private String introduce;
    private String cover;

    @Generated
    public ArticleVO() {
    }

    @Generated
    public Integer getId() {
        return this.id;
    }

    @Generated
    public String getArticleUrl() {
        return this.articleUrl;
    }

    @Generated
    public String getTitle() {
        return this.title;
    }

    @Generated
    public LocalDate getCreateTime() {
        return this.createTime;
    }

    @Generated
    public String getIntroduce() {
        return this.introduce;
    }

    @Generated
    public String getCover() {
        return this.cover;
    }

    @Generated
    public ArticleVO setId(Integer id) {
        this.id = id;
        return this;
    }

    @Generated
    public ArticleVO setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
        return this;
    }

    @Generated
    public ArticleVO setTitle(String title) {
        this.title = title;
        return this;
    }

    @Generated
    public ArticleVO setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
        return this;
    }

    @Generated
    public ArticleVO setIntroduce(String introduce) {
        this.introduce = introduce;
        return this;
    }

    @Generated
    public ArticleVO setCover(String cover) {
        this.cover = cover;
        return this;
    }

    @Generated
    public String toString() {
        return "ArticleVO(id=" + this.getId() + ", articleUrl=" + this.getArticleUrl() + ", title=" + this.getTitle() + ", createTime=" + String.valueOf(this.getCreateTime()) + ", introduce=" + this.getIntroduce() + ", cover=" + this.getCover() + ")";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArticleVO)) {
            return false;
        }
        ArticleVO other = (ArticleVO)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$id = this.getId();
        Integer other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$articleUrl = this.getArticleUrl();
        String other$articleUrl = other.getArticleUrl();
        if (this$articleUrl == null ? other$articleUrl != null : !this$articleUrl.equals(other$articleUrl)) {
            return false;
        }
        String this$title = this.getTitle();
        String other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) {
            return false;
        }
        LocalDate this$createTime = this.getCreateTime();
        LocalDate other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !((Object)this$createTime).equals(other$createTime)) {
            return false;
        }
        String this$introduce = this.getIntroduce();
        String other$introduce = other.getIntroduce();
        if (this$introduce == null ? other$introduce != null : !this$introduce.equals(other$introduce)) {
            return false;
        }
        String this$cover = this.getCover();
        String other$cover = other.getCover();
        return !(this$cover == null ? other$cover != null : !this$cover.equals(other$cover));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof ArticleVO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $articleUrl = this.getArticleUrl();
        result = result * 59 + ($articleUrl == null ? 43 : $articleUrl.hashCode());
        String $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        LocalDate $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : ((Object)$createTime).hashCode());
        String $introduce = this.getIntroduce();
        result = result * 59 + ($introduce == null ? 43 : $introduce.hashCode());
        String $cover = this.getCover();
        result = result * 59 + ($cover == null ? 43 : $cover.hashCode());
        return result;
    }
}

