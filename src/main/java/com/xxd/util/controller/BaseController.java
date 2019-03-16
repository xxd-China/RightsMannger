package com.xxd.util.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.github.pagehelper.PageInfo;
import com.xxd.pojo.User;


/**
 * @author Administrator
 * SecurityUtils.getSubject().login(backUserToken);
 */
public class BaseController {

	protected Map<String, Object> getDataTable(PageInfo<?> pageInfo) {
		Map<String, Object> rspData = new HashMap<String, Object>();
		rspData.put("rows", pageInfo.getList());
		rspData.put("total", pageInfo.getTotal());
		return rspData;
	}
	
    //Subject对象理解为一个用户
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	//获取用户信息
	protected User getCurrentUser() {
		return (User) getSubject().getPrincipal();
	}

	//获取session
	protected Session getSession() {
		return getSubject().getSession();
	}

	protected Session getSession(Boolean flag) {
		return getSubject().getSession(flag);
	}

	//判断登录
	protected void login(AuthenticationToken token) {
		getSubject().login(token);
	}
}
