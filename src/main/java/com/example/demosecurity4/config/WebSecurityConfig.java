package com.example.demosecurity4.config;

import com.example.demosecurity4.user.service.impl.CustomUserDetialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author: Li Hongxing
 * @description: web认证、鉴权配置类
 * @date: Created in 2017/12/01 23:29
 * @modified:
 */
@EnableWebSecurity
/*prePostEnabled 支持SpEL； jsr250Enabled支持jsr250权限注解；  securedEnabled支持@secured注解*/
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource private CustomUserDetialsService customUserDetialsService;
    @Resource private DataSource dataSource;

    @Bean
    @Order(1)
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();

        /*设置createTableOnStartup为true，可建立token相关表格*/
        jdbcTokenRepository.setCreateTableOnStartup(false);
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(){
        return new CustomAuthenticationEventPublisher();
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Bean
    @Order(1)
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // inMemoryManualConfig(auth);  //inMemory模式配置
        customUserDetialsService.setSupportGroup(true);
        auth.userDetailsService(customUserDetialsService).passwordEncoder(passwordEncoder());
        auth.authenticationEventPublisher(authenticationEventPublisher());
    }

    private void inMemoryManualConfig(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                    .withUser("username").password("pass").roles("USER").and()
                    .withUser("admin").password("admin").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .anyRequest().authenticated().and()
                .formLogin().permitAll().and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .key("starzoneCN")
                    .rememberMeParameter("rememberMe")
                    .rememberMeCookieName("warplaneInLaji")
                    .tokenValiditySeconds(60 * 60).and()
                .httpBasic();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("qq123123"));
    }
}
