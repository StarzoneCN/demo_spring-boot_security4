package com.example.demosecurity4.user.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author LiHongxing
 * @since 2018-05-17
 */
public class Users extends Model<Users> implements UserDetails {
    private static final long serialVersionUID = 1L;

    public Users(){}
    public Users(boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired){
    	this.accountNonExpired = accountNonExpired;
    	this.accountNonLocked = accountNonLocked;
    	this.credentialsNonExpired = credentialsNonExpired;
	}

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String username;
	@JsonIgnore
	private String password;
	private String email;
	private String mobile;
	@TableField("country_code")
	private String countryCode;
	private String address;
	private Boolean enabled;

	@TableField(exist = false)
	private Set<GrantedAuthority> authorities;
	@TableField(exist = false)
	private boolean accountNonExpired = false;
	@TableField(exist = false)
	private boolean accountNonLocked = false;
	@TableField(exist = false)
	private boolean credentialsNonExpired = false;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Users{" +
			", id=" + id +
			", username=" + username +
			", password=" + password +
			", email=" + email +
			", mobile=" + mobile +
			", countryCode=" + countryCode +
			", address=" + address +
			", enabled=" + enabled +
			"}";
	}
}
