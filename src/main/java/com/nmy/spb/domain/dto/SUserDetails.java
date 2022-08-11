package com.nmy.spb.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nmy.spb.domain.pojo.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author nmy
 * @title: SUserDetails
 * @date 2022-08-06 13:29
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SUserDetails implements UserDetails {

    private Users users;

    public SUserDetails() {
    }

    public SUserDetails(Users u) {
        this.users = u;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JSONField(serialize = false)
    @Override
    public String getPassword() {
        return users.getUser_password();
    }

    @Override
    public String getUsername() {
        return users.getUser_account();
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
