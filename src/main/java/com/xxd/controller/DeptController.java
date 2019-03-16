package com.xxd.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.pojo.Dept;
import com.xxd.pojo.ResponseBo;
import com.xxd.pojo.Tree;
import com.xxd.service.DeptService;


@Controller
public class DeptController {

	@Resource(name="deptServiceImpl")
	private DeptService deptService;

	//获取部门信息
	@RequestMapping("dept")
	@RequiresPermissions("dept:list")
	public String index() {
		return "system/dept/dept";
	}

	@RequestMapping("dept/tree")
	@ResponseBody
	public ResponseBo getDeptTree() {
		try {
			Tree<Dept> tree = this.deptService.getDeptTree();
			return ResponseBo.ok(tree);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取部门列表失败！");
		}
	}
	@RequestMapping("dept/list")
	@ResponseBody
	public List<Dept> deptList(Dept dept) {
		try {
			return this.deptService.findAllDepts(dept);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
