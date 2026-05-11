/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.util.oss.MinioProperties
 *  lombok.Generated
 *  org.springframework.boot.context.properties.ConfigurationProperties
 */
package com.bilibili.myblogbackend.util.oss;

import lombok.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="minio.blog")
public class MinioProperties
implements Cloneable {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketsName;
    private String image;
    private String video;

    protected MinioProperties clone() throws CloneNotSupportedException {
        return (MinioProperties)super.clone();
    }

    @Generated
    public MinioProperties() {
    }

    @Generated
    public String getEndpoint() {
        return this.endpoint;
    }

    @Generated
    public String getAccessKey() {
        return this.accessKey;
    }

    @Generated
    public String getSecretKey() {
        return this.secretKey;
    }

    @Generated
    public String getBucketsName() {
        return this.bucketsName;
    }

    @Generated
    public String getImage() {
        return this.image;
    }

    @Generated
    public String getVideo() {
        return this.video;
    }

    @Generated
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Generated
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Generated
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Generated
    public void setBucketsName(String bucketsName) {
        this.bucketsName = bucketsName;
    }

    @Generated
    public void setImage(String image) {
        this.image = image;
    }

    @Generated
    public void setVideo(String video) {
        this.video = video;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MinioProperties)) {
            return false;
        }
        MinioProperties other = (MinioProperties)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$endpoint = this.getEndpoint();
        String other$endpoint = other.getEndpoint();
        if (this$endpoint == null ? other$endpoint != null : !this$endpoint.equals(other$endpoint)) {
            return false;
        }
        String this$accessKey = this.getAccessKey();
        String other$accessKey = other.getAccessKey();
        if (this$accessKey == null ? other$accessKey != null : !this$accessKey.equals(other$accessKey)) {
            return false;
        }
        String this$secretKey = this.getSecretKey();
        String other$secretKey = other.getSecretKey();
        if (this$secretKey == null ? other$secretKey != null : !this$secretKey.equals(other$secretKey)) {
            return false;
        }
        String this$bucketsName = this.getBucketsName();
        String other$bucketsName = other.getBucketsName();
        if (this$bucketsName == null ? other$bucketsName != null : !this$bucketsName.equals(other$bucketsName)) {
            return false;
        }
        String this$image = this.getImage();
        String other$image = other.getImage();
        if (this$image == null ? other$image != null : !this$image.equals(other$image)) {
            return false;
        }
        String this$video = this.getVideo();
        String other$video = other.getVideo();
        return !(this$video == null ? other$video != null : !this$video.equals(other$video));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof MinioProperties;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $endpoint = this.getEndpoint();
        result = result * 59 + ($endpoint == null ? 43 : $endpoint.hashCode());
        String $accessKey = this.getAccessKey();
        result = result * 59 + ($accessKey == null ? 43 : $accessKey.hashCode());
        String $secretKey = this.getSecretKey();
        result = result * 59 + ($secretKey == null ? 43 : $secretKey.hashCode());
        String $bucketsName = this.getBucketsName();
        result = result * 59 + ($bucketsName == null ? 43 : $bucketsName.hashCode());
        String $image = this.getImage();
        result = result * 59 + ($image == null ? 43 : $image.hashCode());
        String $video = this.getVideo();
        result = result * 59 + ($video == null ? 43 : $video.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "MinioProperties(endpoint=" + this.getEndpoint() + ", accessKey=" + this.getAccessKey() + ", secretKey=" + this.getSecretKey() + ", bucketsName=" + this.getBucketsName() + ", image=" + this.getImage() + ", video=" + this.getVideo() + ")";
    }
}

