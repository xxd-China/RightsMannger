package com.xxd.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xxd.mapper.MenuMapper;
import com.xxd.pojo.Menu;
import com.xxd.pojo.Tree;
import com.xxd.service.MenuService;
import com.xxd.util.controller.TreeUtils;

import cn.service.common.BaseService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;



@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> findUserPermissions(String userName) {
		return menuMapper.findUserPermissions(userName);
	}
	
	@Override
	public Tree<Menu> getUserMenu(String userName) {
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		List<Menu> menus = this.findUserMenus(userName);
		for (Menu menu : menus) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(menu.getMenuId().toString());
			tree.setParentId(menu.getParentId().toString());
			tree.setText(menu.getMenuName());
			tree.setIcon(menu.getIcon());
			tree.setUrl(menu.getUrl());
			trees.add(tree);
		}
		Tree<Menu> t = TreeUtils.build(trees);
		return t;
	}
	
	
	public List<Menu> findUserMenus(String userName) {
		return this.menuMapper.findUserMenus(userName);
	}
	
	@Override
	public List<Menu> findAllMenus(Menu menu) {
		try {
			Example example = new Example(Menu.class);
			Criteria criteria = example.createCriteria();
			if (StringUtils.isNotBlank(menu.getMenuName())) {
				criteria.andCondition("menu_name=", menu.getMenuName());
			}
			if (StringUtils.isNotBlank(menu.getType())) {
				criteria.andCondition("type=", Long.valueOf(menu.getType()));
			}
			example.setOrderByClause("menu_id");
			return this.selectByExample(example);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new ArrayList<Menu>();
		}
	}
	
	@Override
	public Tree<Menu> getMenuTree() {
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		Example example = new Example(Menu.class);
		example.createCriteria().andCondition("type =", 0);
		example.setOrderByClause("create_time");
		List<Menu> menus = this.menuMapper.selectByExample(example);
		for (Menu menu : menus) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(menu.getMenuId().toString());
			tree.setParentId(menu.getParentId().toString());
			tree.setText(menu.getMenuName());
			trees.add(tree);
		}
		Tree<Menu> t = TreeUtils.build(trees);
		return t;
	}
	
	@Override
	public Menu findById(Long menuId) {
		return this.selectByKey(menuId);
	}
	
	@Override
	public Menu findByNameAndType(String menuName, String type) {
		Example example = new Example(Menu.class);
		example.createCriteria().andCondition("lower(menu_name)=", menuName.toLowerCase()).andEqualTo("type",
				Long.valueOf(type));
		List<Menu> list = this.menuMapper.selectByExample(example);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateMenu(Menu menu) {
		menu.setModifyTime(new Date());
		if (menu.getParentId() == null)
			menu.setParentId(0l);
		this.updateNotNull(menu);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addMenu(Menu menu) {
		menu.setCreateTime(new Date());
		if (menu.getParentId() == null)
			menu.setParentId(0l);
		this.save(menu);
	}

	//未完成
	@Override
	public void deleteMeuns(String ids) {
		// TODO Auto-generated method stub
		
	}

	
}
