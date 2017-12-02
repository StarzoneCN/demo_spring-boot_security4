package com.example.demosecurity4.user.service.impl;

import com.example.demosecurity4.user.entity.CustomGrantedAuthority;
import com.example.demosecurity4.user.entity.User;
import com.example.demosecurity4.user.service.UserService;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Li Hongxing
 * @description: UserDetialsService的jdbc实现
 * @date: Created in 2017/12/02 10:27
 * @modified:
 */
@Service
@Order(1)
public class CustomUserDetialsService implements UserDetailsService, UserService {

    @Resource private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetials = manualUserDetails();
        return userDetials;
    }

    /**
     * 如果没有建立数据库表格，可以使用代码手动创建userDetails来模拟数据库查询
     * 在loadUserByUsername中引用此方法
     */
    private User manualUserDetails() {
        User userDetials = new User();
        userDetials.setUsername("admin");
        userDetials.setPassword("$2a$10$Nnwx70r6csJlAX1.oNgbVOSN8JO5wFZmsubzXlxoXv9eUNlMU6buq");
        userDetials.setAccountNonExpired(true);
        userDetials.setAccountNonLocked(true);
        userDetials.setCredentialsNonExpired(true);
        userDetials.setEnabled(true);
        List<CustomGrantedAuthority> authorities = new ArrayList<CustomGrantedAuthority>(2);
        CustomGrantedAuthority authority = new CustomGrantedAuthority();
        authority.setAuthority("ROLE_ADMIN");  // 这里要添加上ROLE_前缀
        authorities.add(authority);
        userDetials.setAuthorities(authorities);
        return userDetials;
    }

    @Override
    public int insert(User user) {
        String passStrBeforeEncode = user.getPassword();
        user.setPassword(passwordEncoder.encode(passStrBeforeEncode));
//        save(user);  // 保存到表中
        return 0;
    }
}
