package com.example.demo.security.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.demo.security.UserInfo;

public class MyUserDetails extends User{
	private static final long serialVersionUID = 1L;

	private final UserInfo userinfo;

	public MyUserDetails(UserInfo userInfo,  List<? extends GrantedAuthority> authorityList) {
		super(userInfo.getUserName(), userInfo.getPassword(), true, true, true, true, authorityList);
		this.userinfo = userInfo;
	}

	public boolean isAccountNonExpired() {
		return super.isAccountNonExpired();
	}
	public boolean isAccountNonLocked() {
		return super.isAccountNonLocked();
	}
	public boolean isCredentialsNonExpired() {
		return super.isCredentialsNonExpired();
	}

	public UserInfo getUserInfo() {
		return userinfo;
	}


}
