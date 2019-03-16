package com.xxd.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxd.pojo.QueryRequest;
import com.xxd.pojo.ResponseBo;
import com.xxd.pojo.User;
import com.xxd.service.UserService;
import com.xxd.util.controller.BaseController;



@Controller
public class UserController extends BaseController {

	@Resource(name="userServiceImpl")
	private UserService userService;

	@RequestMapping("user")
	@RequiresPermissions("user:list")
	public String index(Model model) {
		User user = super.getCurrentUser();
		model.addAttribute("user", user);
		return "system/user/user";
	}

	@RequestMapping("user/list")
	@ResponseBody
	public Map<String, Object> userList(QueryRequest request, User user) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		List<User> list = this.userService.findUserWithDept(user);
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		return getDataTable(pageInfo);
	}
	
	//检测用户名是否存在
	@RequestMapping("user/checkUserName")
	@ResponseBody
	public boolean checkUserName(String username, String oldusername) {
		if (StringUtils.isNotBlank(oldusername) && username.equalsIgnoreCase(oldusername)) {
			return true;
		}
		User result = this.userService.findByName(username);
		if (result != null)
			return false;
		return true;
	}
	
	//新增用户
	@RequiresPermissions("user:add")
	@RequestMapping("user/add")
	@ResponseBody
	public ResponseBo addUser(User user, Long[] roles) {
		try {
			if ("on".equalsIgnoreCase(user.getStatus()))
				user.setStatus("1");
			else
				user.setStatus("0");
			this.userService.addUser(user, roles);
			return ResponseBo.ok("新增用户成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("新增用户失败，请联系网站管理员！");
		}
	}
	
    //獲取用戶信息（帶角色名）
	@RequestMapping("user/getUser")
	@ResponseBody
	public ResponseBo getUser(Long userId) {
		try { 
			User user = this.userService.findById(userId);
			return ResponseBo.ok(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取用户信息失败，请联系网站管理员！");
		}
	}
	
	//修改用户
	@RequiresPermissions("user:update")
	@RequestMapping("user/update")
	@ResponseBody
	public ResponseBo updateUser(User user, Long[] rolesSelect) {
		try {
			if ("on".equalsIgnoreCase(user.getStatus()))
				user.setStatus("1");
			else
				user.setStatus("0");
			this.userService.updateUser(user, rolesSelect);
			return ResponseBo.ok("修改用户成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("修改用户失败，请联系网站管理员！");
		}
	}

	//删除用户
	@RequiresPermissions("user:delete")
	@RequestMapping("user/delete")
	@ResponseBody
	public ResponseBo deleteUsers(String ids) {
		try {
			this.userService.deleteUsers(ids);
			return ResponseBo.ok("删除用户成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("删除用户失败，请联系网站管理员！");
		}
	}
	
	@RequestMapping("user/regist")
	@ResponseBody
	public ResponseBo regist(User user) {
		try {
			User result = this.userService.findByName(user.getUsername());
			if (result != null) {
				return ResponseBo.warn("该用户名已被使用！");
			}
			this.userService.registUser(user);
			return ResponseBo.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("注册失败，请联系网站管理员！");
		}
	}

}
