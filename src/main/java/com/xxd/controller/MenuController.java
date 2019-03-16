package com.xxd.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.pojo.Menu;
import com.xxd.pojo.ResponseBo;
import com.xxd.pojo.Tree;
import com.xxd.service.MenuService;


@Controller
public class MenuController{

	@Resource(name = "menuServiceImpl")
	private MenuService menuService;

	//获取菜单信息
	@RequestMapping("menu")
	@RequiresPermissions("menu:list")
	public String index() {
		return "system/menu/menu";
	}
	//獲取菜單信息
	@RequestMapping("menu/getMenu")
	@ResponseBody
	public ResponseBo getMenu(Long menuId) {
		try {
			Menu menu = this.menuService.findById(menuId);
			return ResponseBo.ok(menu);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取信息失败，请联系网站管理员！");
		}
	}
	@RequestMapping("menu/tree")
	@ResponseBody
	public ResponseBo getMenuTree() {
		try {
			Tree<Menu> tree = this.menuService.getMenuTree();
			return ResponseBo.ok(tree);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取菜单列表失败！");
		}
	}
	
	@RequestMapping("menu/getUserMenu")
	@ResponseBody
	public ResponseBo getUserMenu(String userName) {
		try {
			Tree<Menu> tree = menuService.getUserMenu(userName);
			return ResponseBo.ok(tree);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取用户菜单失败！");
		}
	}

	@RequestMapping("menu/list")
	@ResponseBody
	public List<Menu> menuList(Menu menu) {
		try {
			return this.menuService.findAllMenus(menu);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping("menu/checkMenuName")
	@ResponseBody
	public boolean checkMenuName(String menuName, String type, String oldMenuName) {
		if (StringUtils.isNotBlank(oldMenuName) && menuName.equalsIgnoreCase(oldMenuName)) {
			return true;
		}
		Menu result = this.menuService.findByNameAndType(menuName, type);
		if (result != null)
			return false;
		return true;
	}

	//新增菜单/按钮
	@RequiresPermissions("menu:add")
	@RequestMapping("menu/add")
	@ResponseBody
	public ResponseBo addMenu(Menu menu) {
		String name = "";
		if (Menu.TYPE_MENU.equals(menu.getType()))
			name = "菜单";
		else
			name = "按钮";
		try {
			this.menuService.addMenu(menu);
			return ResponseBo.ok("新增" + name + "成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("新增" + name + "失败，请联系网站管理员！");
		}
	}

	//刪除
	@RequiresPermissions("menu:delete")
	@RequestMapping("menu/delete")
	@ResponseBody
	public ResponseBo deleteMenus(String ids) {
		try {
			this.menuService.deleteMeuns(ids);
			return ResponseBo.ok("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("删除失败，请联系网站管理员！");
		}
	}
//	
	//修改菜单/按钮
	@RequiresPermissions("menu:update")
	@RequestMapping("menu/update")
	@ResponseBody
	public ResponseBo updateMenu(Menu menu) {
		String name = "";
		if (Menu.TYPE_MENU.equals(menu.getType()))
			name = "菜单";
		else
			name = "按钮";
		try {
			this.menuService.updateMenu(menu);
			return ResponseBo.ok("修改" + name + "成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("修改" + name + "失败，请联系网站管理员！");
		}
	}
}
