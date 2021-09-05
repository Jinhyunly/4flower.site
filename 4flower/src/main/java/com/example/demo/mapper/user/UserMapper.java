package com.example.demo.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.example.demo.entity.user.User;
import com.example.demo.security.UserInfo;

@Component
@Mapper
public interface UserMapper {
	User findUserByLoginId(@Param("loginId") String loginId);

	UserInfo findUserByLoginId2(@Param("loginId") String loginId);
  int setUserInfo(@Param("param") User param);

}
