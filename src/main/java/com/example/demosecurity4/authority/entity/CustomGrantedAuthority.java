package com.example.demosecurity4.authority.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author: Li Hongxing
 * @description: GrantedAuthority实现类
 * @date: Created in 2017/12/02 10:35
 * @modified:
 */
public class CustomGrantedAuthority implements GrantedAuthority {

    private Integer id;
    private Integer userId;
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
