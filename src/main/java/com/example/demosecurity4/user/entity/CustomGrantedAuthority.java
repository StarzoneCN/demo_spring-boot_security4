package com.example.demosecurity4.user.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author: Li Hongxing
 * @description: GrantedAuthority实现类
 * @date: Created in 2017/12/02 10:35
 * @modified:
 */
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
