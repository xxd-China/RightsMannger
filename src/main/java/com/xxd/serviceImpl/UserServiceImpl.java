package com.xxd.serviceImpl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xxd.mapper.UserMapper;
import com.xxd.mapper.UserRoleMapper;
import com.xxd.pojo.User;
import com.xxd.pojo.UserRole;
import com.xxd.service.UserRoleService;
import com.xxd.service.UserService;


import com.xxd.pojo.UserWithRole;

import cn.service.common.BaseService;
import tk.mybatis.mapper.entity.Example;




@Service
public class UserServiceImpl extends BaseService<User> implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Resource(name="userRoleServiceImpl")
	private UserRoleService userRoleService;
	
    //查询用户
	public User findByName(String userName) {
		User user = userMapper.findbyName(userName);
		return user;
	}
	
	public List<User> findUserWithDept(User user) {
		try {
			return this.userMapper.findUserWithDept(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<User>();
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUser(User user, Long[] roles) {
		user.setCrateTime(new Date());
		user.setTheme(User.DEFAULT_THEME);
		user.setAvatar(User.DEFAULT_AVATAR);
		user.setPassword(com.xxd.util.controller.MD5Utils.md5(user.getPassword()));
		this.save(user);
		for (Long roleId : roles) {
			UserRole ur = new UserRole();
			ur.setUserId(user.getUserId());
			ur.setRoleId(roleId);
			this.userRoleMapper.insert(ur);
		}
	}
	
	@Override
	public UserWithRole findById(Long userId) {
		List<UserWithRole> list = this.userMapper.findUserWithRole(userId);
		List<Long> roleList = new ArrayList<Long>();
		for (UserWithRole uwr : list) {
			roleList.add(uwr.getRoleId());
		}
		if (list.size() == 0) {
			return null;
		}
		UserWithRole userWithRole = list.get(0);
		userWithRole.setRoleIds(roleList);
		return userWithRole;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateUser(User user, Long[] roles) {
		user.setPassword(null);
		user.setUsername(null);
		user.setModifyTime(new Date());
		this.updateNotNull(user);
		Example example = new Example(UserRole.class);
		example.createCriteria().andCondition("user_id=", user.getUserId());
		this.userRoleMapper.deleteByExample(example);
		for (Long roleId : roles) {
			UserRole ur = new UserRole();
			ur.setUserId(user.getUserId());
			ur.setRoleId(roleId);
			this.userRoleMapper.insert(ur);
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteUsers(String userIds) {
		List<String> list = Arrays.asList(userIds.split(","));
		this.batchDelete(list, "userId", User.class);

		this.userRoleService.deleteUserRolesByUserId(userIds);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void registUser(User user) {
		user.setCrateTime(new Date());
		user.setTheme(User.DEFAULT_THEME);
		user.setAvatar(User.DEFAULT_AVATAR);
		user.setSsex(User.SEX_UNKNOW);
		user.setPassword(com.xxd.util.controller.MD5Utils.md5(user.getPassword()));
		this.save(user);
		UserRole ur = new UserRole();
		ur.setUserId(user.getUserId());
		ur.setRoleId(3l);
		this.userRoleMapper.insert(ur);
	}
	
	

}
