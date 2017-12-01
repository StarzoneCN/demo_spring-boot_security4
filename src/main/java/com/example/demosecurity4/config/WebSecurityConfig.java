package com.example.demosecurity4.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: Li Hongxing
 * @description: web认证、鉴权配置类
 * @date: Created in 2017/12/01 23:29
 * @modified:
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                    .withUser("username").password("pass").roles("USER").and()
                    .withUser("admin").password("admin").roles("USER", "ADMIN");
    }
}
