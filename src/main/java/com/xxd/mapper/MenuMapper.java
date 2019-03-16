package com.xxd.mapper;

import java.util.List;

import com.xxd.pojo.Menu;

import cn.mapper.common.MyMapper;


public interface MenuMapper extends MyMapper<Menu>{
	
	List<Menu> findUserPermissions(String userName);
	
	List<Menu> findUserMenus(String userName);

}