package com.xxd.mapper;

import java.util.List;

import com.xxd.pojo.Role;
import com.xxd.pojo.RoleWithMenu;


public interface RoleMapper{
	
	List<Role> findUserRole(String userName);
	
	List<RoleWithMenu> findById(Long roleId);
	
	List<Role> findAllRole();
}