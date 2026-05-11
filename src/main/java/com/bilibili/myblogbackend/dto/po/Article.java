package com.bilibili.myblogbackend.dto.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Generated;

@TableName(value="article")
public class Article
implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value="id", type=IdType.AUTO)
    private Integer id;
    private String articleUrl;
    private LocalDate createTime;
    private Integer type;
    private String title;
    private String introduce;
    private String cover;

    public Article() {
    }

    @Generated
    public static ArticleBuilder builder() {
        return new ArticleBuilder();
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
    public LocalDate getCreateTime() {
        return this.createTime;
    }

    @Generated
    public Integer getType() {
        return this.type;
    }

    @Generated
    public String getTitle() {
        return this.title;
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
    public Article setId(Integer id) {
        this.id = id;
        return this;
    }

    @Generated
    public Article setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
        return this;
    }

    @Generated
    public Article setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
        return this;
    }

    @Generated
    public Article setType(Integer type) {
        this.type = type;
        return this;
    }

    @Generated
    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    @Generated
    public Article setIntroduce(String introduce) {
        this.introduce = introduce;
        return this;
    }

    @Generated
    public Article setCover(String cover) {
        this.cover = cover;
        return this;
    }

    @Generated
    public String toString() {
        return "Article(id=" + this.getId() + ", articleUrl=" + this.getArticleUrl() + ", createTime=" + String.valueOf(this.getCreateTime()) + ", type=" + this.getType() + ", title=" + this.getTitle() + ", introduce=" + this.getIntroduce() + ", cover=" + this.getCover() + ")";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Article)) {
            return false;
        }
        Article other = (Article)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$id = this.getId();
        Integer other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Integer this$type = this.getType();
        Integer other$type = other.getType();
        if (this$type == null ? other$type != null : !((Object)this$type).equals(other$type)) {
            return false;
        }
        String this$articleUrl = this.getArticleUrl();
        String other$articleUrl = other.getArticleUrl();
        if (this$articleUrl == null ? other$articleUrl != null : !this$articleUrl.equals(other$articleUrl)) {
            return false;
        }
        LocalDate this$createTime = this.getCreateTime();
        LocalDate other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !((Object)this$createTime).equals(other$createTime)) {
            return false;
        }
        String this$title = this.getTitle();
        String other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) {
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
        return other instanceof Article;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Integer $type = this.getType();
        result = result * 59 + ($type == null ? 43 : ((Object)$type).hashCode());
        String $articleUrl = this.getArticleUrl();
        result = result * 59 + ($articleUrl == null ? 43 : $articleUrl.hashCode());
        LocalDate $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : ((Object)$createTime).hashCode());
        String $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        String $introduce = this.getIntroduce();
        result = result * 59 + ($introduce == null ? 43 : $introduce.hashCode());
        String $cover = this.getCover();
        result = result * 59 + ($cover == null ? 43 : $cover.hashCode());
        return result;
    }

    @Generated
    public Article(Integer id, String articleUrl, LocalDate createTime, Integer type, String title, String introduce, String cover) {
        this.id = id;
        this.articleUrl = articleUrl;
        this.createTime = createTime;
        this.type = type;
        this.title = title;
        this.introduce = introduce;
        this.cover = cover;
    }

    public static class ArticleBuilder {
        private Integer id;
        private String articleUrl;
        private LocalDate createTime;
        private Integer type;
        private String title;
        private String introduce;
        private String cover;

        ArticleBuilder() {
        }

        public ArticleBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ArticleBuilder articleUrl(String articleUrl) {
            this.articleUrl = articleUrl;
            return this;
        }

        public ArticleBuilder createTime(LocalDate createTime) {
            this.createTime = createTime;
            return this;
        }

        public ArticleBuilder type(Integer type) {
            this.type = type;
            return this;
        }

        public ArticleBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ArticleBuilder introduce(String introduce) {
            this.introduce = introduce;
            return this;
        }

        public ArticleBuilder cover(String cover) {
            this.cover = cover;
            return this;
        }

        public Article build() {
            return new Article(this.id, this.articleUrl, this.createTime, this.type, this.title, this.introduce, this.cover);
        }
    }
}
