package com.xxd.service;

import java.util.List;

import com.xxd.pojo.Menu;
import com.xxd.pojo.Tree;


public interface MenuService{
	
	List<Menu> findUserPermissions(String userName);

	List<Menu> findUserMenus(String userName);

	List<Menu> findAllMenus(Menu menu);
	
	Tree<Menu> getMenuTree();
	
	Tree<Menu> getUserMenu(String userName);

	Menu findById(Long menuId);

	Menu findByNameAndType(String menuName, String type);

	void updateMenu(Menu menu);

	void deleteMeuns(String ids);

	void addMenu(Menu menu);
}
