 package com.xxd.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.xxd.pojo.Menu;
import com.xxd.pojo.Role;
import com.xxd.pojo.User;
import com.xxd.service.MenuService;
import com.xxd.service.RoleService;
import com.xxd.service.UserService;


public class ShiroRealm extends AuthorizingRealm {

	@Resource(name = "userServiceImpl")
	private UserService userService;
	@Resource(name = "roleServiceImpl")
	private RoleService roleService;
	@Resource(name = "menuServiceImpl")
	private MenuService menuService;

//	会进入授权方法一共有三种情况！
//	1、subject.hasRole(“admin”) 或 subject.isPermitted(“admin”)：自己去调用这个是否有什么角色或者是否有什么权限的时候；
//	2、@RequiresRoles("admin") ：在方法上加注解的时候；
//	3、[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]：在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候。
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		String userName = user.getUsername();

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		//角色
		List<Role> roleList = this.roleService.findUserRole(userName);
		Set<String> roleSet = new HashSet<String>();
		for (Role r : roleList) {
			roleSet.add(r.getRoleName());
			//System.out.println("角色：" + r.getRoleName());
		}
		simpleAuthorizationInfo.setRoles(roleSet);

		//菜单
		List<Menu> permissionList = menuService.findUserPermissions(userName);
		Set<String> permissionSet = new HashSet<String>();
		for (Menu m : permissionList) {
			permissionSet.add(m.getPerms());
			//System.out.println("权限： " + m.getPerms());
		}
		simpleAuthorizationInfo.setStringPermissions(permissionSet);
		return simpleAuthorizationInfo;
	}

    //登錄
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		User user = userService.findByName(userName);

		if (user == null) {
			throw new UnknownAccountException("用户名或密码错误！");
		}
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("用户名或密码错误！");
		}
		if ("0".equals(user.getStatus())) {
			throw new LockedAccountException("账号已被锁定,请联系管理员！");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
