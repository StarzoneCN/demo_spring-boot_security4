package com.example.demosecurity4.user.service.impl;

import com.example.demosecurity4.authority.mapper.AuthorityMapper;
import com.example.demosecurity4.group.mapper.GroupMapper;
import com.example.demosecurity4.authority.entity.CustomGrantedAuthority;
import com.example.demosecurity4.user.entity.User;
import com.example.demosecurity4.user.mapper.UserMapper;
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
public class CustomUserDetialsService implements UserDetailsService, UserService {

    private boolean supportGroup;
    @Resource private PasswordEncoder passwordEncoder;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource private UserMapper userMapper;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource private AuthorityMapper authorityMapper;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource private GroupMapper groupMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetials = userMapper.selectByUsername(username);
        if (userDetials == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        userDetials.setAccountNonExpired(true);
        userDetials.setAccountNonLocked(true);
        userDetials.setCredentialsNonExpired(true);
        userDetials.setEnabled(true);
        List<CustomGrantedAuthority> authorities = getAuthorities(userDetials);
        userDetials.setAuthorities(authorities);
        return userDetials;
    }

    private List<CustomGrantedAuthority> getAuthorities(User userDetials) {
        if (!supportGroup) {
            return authorityMapper.selectByUserId(userDetials.getId());
        }
        return groupMapper.selectGroupAuthoritiesByUserId(userDetials.getId());
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
