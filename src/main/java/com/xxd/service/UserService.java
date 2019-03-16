package com.xxd.service;


import java.util.List;

import com.xxd.pojo.User;



public interface UserService{

	//username查詢用戶
	User findByName(String userName);
	
    //查询用户（带有部门名）
	List<User> findUserWithDept(User user);

    //新增用户
	void addUser(User user, Long[] roles);

    //根据id查用户信息
	User findById(Long userId);

    //更新用戶
	void updateUser(User user, Long[] rolesSelect);

    //刪除
	void deleteUsers(String ids);
	
    //註冊
	void registUser(User user);

	
}
