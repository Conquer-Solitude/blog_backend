/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.dto.CustomAuthtication
 *  com.bilibili.myblogbackend.dto.po.User
 *  com.bilibili.myblogbackend.dto.vo.LoginUser
 *  lombok.Generated
 *  org.springframework.security.core.GrantedAuthority
 *  org.springframework.security.core.userdetails.UserDetails
 */
package com.bilibili.myblogbackend.dto.vo;

import com.bilibili.myblogbackend.dto.CustomAuthtication;
import com.bilibili.myblogbackend.dto.po.User;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUser
implements UserDetails {
    private User user;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.user.getId() == 1) {
            ArrayList<CustomAuthtication> list = new ArrayList<CustomAuthtication>();
            list.add(new CustomAuthtication());
            return list;
        }
        return null;
    }

    public String getPassword() {
        return this.user.getPassword();
    }

    public String getUsername() {
        return this.user.getEmail();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    @Generated
    public User getUser() {
        return this.user;
    }

    @Generated
    public void setUser(User user) {
        this.user = user;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LoginUser)) {
            return false;
        }
        LoginUser other = (LoginUser)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        User this$user = this.getUser();
        User other$user = other.getUser();
        return !(this$user == null ? other$user != null : !this$user.equals(other$user));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof LoginUser;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        User $user = this.getUser();
        result = result * 59 + ($user == null ? 43 : $user.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "LoginUser(user=" + String.valueOf(this.getUser()) + ")";
    }

    @Generated
    public LoginUser(User user) {
        this.user = user;
    }
}

