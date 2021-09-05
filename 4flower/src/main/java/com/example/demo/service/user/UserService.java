package com.example.demo.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.user.User;
import com.example.demo.mapper.user.UserMapper;

@Service
//public class UserService implements UserDetailsService {
public class UserService {
	@Autowired
	private UserMapper userMapper;

//	@Autowired
//	private RoleMapper roleMapper;

//	@Autowired
//	private UserRoleMapper userRoleMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User findUserByLoginId(String loginId) {
		return userMapper.findUserByLoginId(loginId);
	}

	@Transactional
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		userMapper.setUserInfo(user);

//		Role role = roleMapper.getRoleInfo("ADMIN");

//		UserRole userRole = new UserRole();
//		userRole.setRoleId(role.getId());
//		userRole.setUserId(user.getId());

//		userRoleMapper.setUserRoleInfo(userRole);
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		User user = userMapper.findUserByLoginId(username);
//
//		if(user == null) {
//			throw new UsernameNotFoundException("username not found");
//		}
//
//		return new UserPrincipal(user);
//	}

}