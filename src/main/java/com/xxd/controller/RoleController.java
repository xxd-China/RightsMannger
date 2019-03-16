package com.xxd.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxd.pojo.QueryRequest;
import com.xxd.pojo.Role;
import com.xxd.service.RoleService;
import com.xxd.util.controller.BaseController;


@Controller
public class RoleController extends BaseController {

	@Resource(name="roleServiceImpl")
	private RoleService roleService;

	
	@RequestMapping("role")
	@RequiresPermissions("role:list")
	public String index() {
		return "system/role/role";
	}

	@RequestMapping("role/list")
	@ResponseBody
	public Map<String, Object> roleList(QueryRequest request, Role role) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		List<Role> list = this.roleService.findAllRole(role);
		PageInfo<Role> pageInfo = new PageInfo<Role>(list);
		return getDataTable(pageInfo);
	}
	
}
