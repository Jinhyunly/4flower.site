package com.example.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.user.User;
import com.example.demo.mapper.user.UserMapper;
import com.example.demo.security.UserInfo;

@Service
public class UserInfoService {
@Autowired
UserMapper dao;

public UserInfo selectDetailByUserCd(String loginId) {
	User userBean = new User();
	userBean.setLoginId(loginId);

	UserInfo userinfo = dao.findUserByLoginId2(userBean.getLoginId());
	return userinfo;
}
}
