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
    private boolean supportGroup;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetials = manualUserDetails(username);
        return userDetials;
    }

    /**
     * 如果没有建立数据库表格，可以使用代码手动创建userDetails来模拟数据库查询
     * 在loadUserByUsername中引用此方法
     */
    private User manualUserDetails(String username) {
        User userDetials = new User();
        userDetials.setUsername(username);
        userDetials.setPassword(passwordEncoder.encode(username)); // 密码和username相同
        userDetials.setAccountNonExpired(true);
        userDetials.setAccountNonLocked(true);
        userDetials.setCredentialsNonExpired(true);
        userDetials.setEnabled(true);
        List<CustomGrantedAuthority> authorities = new ArrayList<CustomGrantedAuthority>(2);
        CustomGrantedAuthority authority = new CustomGrantedAuthority();
        authority.setAuthority("admin".equalsIgnoreCase(username) ? "ROLE_ADMIN" : "ROLE_USER");  // 这里要添加上ROLE_前缀
        authorities.add(authority);
        userDetials.setAuthorities(authorities);
        System.out.println("supportGroup = " + supportGroup);
        return userDetials;
    }

    @Override
    public int insert(User user) {
        String passStrBeforeEncode = user.getPassword();
        user.setPassword(passwordEncoder.encode(passStrBeforeEncode));
//        save(user);  // 保存到表中
        return 0;
    }

    public boolean isSupportGroup() {
        return supportGroup;
    }

    public void setSupportGroup(boolean supportGroup) {
        this.supportGroup = supportGroup;
    }
}
