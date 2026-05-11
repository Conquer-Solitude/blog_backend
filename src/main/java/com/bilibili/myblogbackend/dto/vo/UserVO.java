/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.dto.vo.UserVO
 *  lombok.Generated
 */
package com.bilibili.myblogbackend.dto.vo;

import java.io.Serializable;
import lombok.Generated;

public class UserVO
implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String email;
    private Integer isForbidden;
    private String password;
    private String code;

    @Generated
    public UserVO() {
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
    public String getPassword() {
        return this.password;
    }

    @Generated
    public String getCode() {
        return this.code;
    }

    @Generated
    public UserVO setId(Integer id) {
        this.id = id;
        return this;
    }

    @Generated
    public UserVO setEmail(String email) {
        this.email = email;
        return this;
    }

    @Generated
    public UserVO setIsForbidden(Integer isForbidden) {
        this.isForbidden = isForbidden;
        return this;
    }

    @Generated
    public UserVO setPassword(String password) {
        this.password = password;
        return this;
    }

    @Generated
    public UserVO setCode(String code) {
        this.code = code;
        return this;
    }

    @Generated
    public String toString() {
        return "UserVO(id=" + this.getId() + ", email=" + this.getEmail() + ", isForbidden=" + this.getIsForbidden() + ", password=" + this.getPassword() + ", code=" + this.getCode() + ")";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserVO)) {
            return false;
        }
        UserVO other = (UserVO)o;
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
        String this$password = this.getPassword();
        String other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) {
            return false;
        }
        String this$code = this.getCode();
        String other$code = other.getCode();
        return !(this$code == null ? other$code != null : !this$code.equals(other$code));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof UserVO;
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
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        return result;
    }
}

