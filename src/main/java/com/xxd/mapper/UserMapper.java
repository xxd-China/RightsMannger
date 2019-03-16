package com.xxd.mapper;

import java.util.List;

import com.xxd.pojo.User;
import com.xxd.pojo.UserWithRole;

import cn.mapper.common.MyMapper;

public interface UserMapper extends MyMapper<User> {
	
	List<User> findUserWithDept(User user);
	
	//List<UserWithRole> findUserWithRole(Long userId);
	
	User findUserProfile(User user);

	//根据用戶名查詢
	User findbyName(String userName);

	//带角色名的用户信息
	List<UserWithRole> findUserWithRole(Long userId);
}