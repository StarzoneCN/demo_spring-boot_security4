package com.example.demosecurity4.user.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.example.demosecurity4.user.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author: Li Hongxing
 * @description: UserDetialsService的jdbc实现
 * @date: Created in 2017/12/02 10:27
 * @modified:
 */
@Service
@Primary
public class CustomUserDetialsService extends JdbcUserDetailsManager {
    private boolean usernameBasedPrimaryKey = true;

    @Resource private DataSource customDataSource;
    @Autowired private UserService userService;

    @PostConstruct
    public void init(){
        this.setDataSource(customDataSource);
    }

    /**
     * Executes the SQL <tt>usersByUsernameQuery</tt> and returns a list of UserDetails
     * objects. There should normally only be one matching user.
     */
    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        Condition condition = new Condition();
        condition.eq("username", username);
        return userService.selectList(condition);
    }

    /**
     * Can be overridden to customize the creation of the final UserDetailsObject which is
     * returned by the <tt>loadUserByUsername</tt> method.
     *
     * @param username the name originally passed to loadUserByUsername
     * @param userFromUserQuery the object returned from the execution of the
     * @param combinedAuthorities the combined array of authorities from all the authority
     * loading queries.
     * @return the final UserDetails which should be used in the system.
     */
    protected UserDetails createUserDetails(String username,
                                            UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
        String returnUsername = userFromUserQuery.getUsername();

        if (!this.usernameBasedPrimaryKey) {
            returnUsername = username;
        }

        Users users = new Users(true, true, true){{
            this.setPassword(userFromUserQuery.getPassword());
            this.setEnabled(userFromUserQuery.isEnabled());
            this.setAuthorities(sortAuthorities(combinedAuthorities));
            Users tempUsers = (Users)userFromUserQuery;
            this.setAddress(tempUsers.getAddress());
            this.setCountryCode(tempUsers.getCountryCode());
            this.setEmail(tempUsers.getEmail());
            this.setId(tempUsers.getId());
            this.setMobile(tempUsers.getMobile());
        }};
        users.setUsername(returnUsername);
        return users;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per
        // UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(
                new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority,
                    "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>,
            Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to
            // the set.
            // If the authority is null, it is a custom authority and should precede
            // others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }

    @Override
    public boolean isUsernameBasedPrimaryKey() {
        return usernameBasedPrimaryKey;
    }

    @Override
    public void setUsernameBasedPrimaryKey(boolean usernameBasedPrimaryKey) {
        this.usernameBasedPrimaryKey = usernameBasedPrimaryKey;
    }
}
