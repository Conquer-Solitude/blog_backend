/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.dto.UserDto
 *  lombok.Generated
 */
package com.bilibili.myblogbackend.dto;

import java.io.Serializable;
import lombok.Generated;

public class UserDto
implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String email;
    private Integer isForbidden;
    private String jwt;

    @Generated
    public UserDto() {
    }

    @Generated
    public Integer getId() {
        return this.id;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public Integer getIsForbidden() {
        return this.isForbidden;
    }

    @Generated
    public String getJwt() {
        return this.jwt;
    }

    @Generated
    public UserDto setId(Integer id) {
        this.id = id;
        return this;
    }

    @Generated
    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    @Generated
    public UserDto setIsForbidden(Integer isForbidden) {
        this.isForbidden = isForbidden;
        return this;
    }

    @Generated
    public UserDto setJwt(String jwt) {
        this.jwt = jwt;
        return this;
    }

    @Generated
    public String toString() {
        return "UserDto(id=" + this.getId() + ", email=" + this.getEmail() + ", isForbidden=" + this.getIsForbidden() + ", jwt=" + this.getJwt() + ")";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserDto)) {
            return false;
        }
        UserDto other = (UserDto)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$id = this.getId();
        Integer other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Integer this$isForbidden = this.getIsForbidden();
        Integer other$isForbidden = other.getIsForbidden();
        if (this$isForbidden == null ? other$isForbidden != null : !((Object)this$isForbidden).equals(other$isForbidden)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$jwt = this.getJwt();
        String other$jwt = other.getJwt();
        return !(this$jwt == null ? other$jwt != null : !this$jwt.equals(other$jwt));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof UserDto;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Integer $isForbidden = this.getIsForbidden();
        result = result * 59 + ($isForbidden == null ? 43 : ((Object)$isForbidden).hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $jwt = this.getJwt();
        result = result * 59 + ($jwt == null ? 43 : $jwt.hashCode());
        return result;
    }
}

