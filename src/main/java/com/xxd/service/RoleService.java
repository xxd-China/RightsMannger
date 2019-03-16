package com.xxd.service;

import java.util.List;

import com.xxd.pojo.Role;
import com.xxd.pojo.RoleWithMenu;

public interface RoleService{

	List<Role> findUserRole(String userName);

	List<Role> findAllRole(Role role);
	
	RoleWithMenu findRoleWithMenus(Long roleId);

	Role findByName(String roleName);

	void addRole(Role role, Long[] menuIds);
	
	void updateRole(Role role, Long[] menuIds);

	void deleteRoles(String roleIds);
}
