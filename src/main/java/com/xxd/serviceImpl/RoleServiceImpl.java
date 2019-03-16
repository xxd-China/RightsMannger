package com.xxd.serviceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xxd.mapper.RoleMapper;
import com.xxd.pojo.Role;
import com.xxd.pojo.RoleWithMenu;
import com.xxd.service.RoleService;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl  implements RoleService {

	@Autowired
	private RoleMapper roleMapper;


	@Override
	public List<Role> findUserRole(String userName) {
		return this.roleMapper.findUserRole(userName);
	}

	@Override
	public List<Role> findAllRole(Role role) {
		
		List<Role> list = roleMapper.findAllRole();
		return list;
	}

	@Override
	public RoleWithMenu findRoleWithMenus(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findByName(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRole(Role role, Long[] menuIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRole(Role role, Long[] menuIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoles(String roleIds) {
		// TODO Auto-generated method stub
		
	}


}
