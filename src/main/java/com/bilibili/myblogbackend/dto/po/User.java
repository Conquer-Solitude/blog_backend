/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotation.IdType
 *  com.baomidou.mybatisplus.annotation.TableId
 *  com.baomidou.mybatisplus.annotation.TableName
 *  com.bilibili.myblogbackend.dto.po.User
 *  lombok.Generated
 */
package com.bilibili.myblogbackend.dto.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Generated;

@TableName(value="user")
public class User
implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value="id", type=IdType.AUTO)
    private Integer id;
    private String email;
    private Integer isForbidden;
    private String password;

    @Generated
    public User() {
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
    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    @Generated
    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @Generated
    public User setIsForbidden(Integer isForbidden) {
        this.isForbidden = isForbidden;
        return this;
    }

    @Generated
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Generated
    public String toString() {
        return "User(id=" + this.getId() + ", email=" + this.getEmail() + ", isForbidden=" + this.getIsForbidden() + ", password=" + this.getPassword() + ")";
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User other = (User)o;
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
        return !(this$password == null ? other$password != null : !this$password.equals(other$password));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof User;
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
        return result;
    }
}

