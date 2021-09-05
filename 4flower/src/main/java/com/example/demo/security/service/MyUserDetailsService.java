package com.example.demo.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.security.UserInfo;

public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	private UserInfoService userInfoService;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

		UserInfo userInfo = userInfoService.selectDetailByUserCd(loginId);

		if(userInfo == null) {
			throw new UsernameNotFoundException("");
		}
		List<SimpleGrantedAuthority> authorityList = new ArrayList<SimpleGrantedAuthority>();
		authorityList.add(new SimpleGrantedAuthority(userInfo.getLoginId()));

		return new MyUserDetails(userInfo, authorityList);
	}

}
