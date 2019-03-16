package com.xxd.service;

import java.util.List;

import com.xxd.pojo.Dept;
import com.xxd.pojo.Tree;


public interface DeptService {

	Tree<Dept> getDeptTree();
	
	List<Dept> findAllDepts(Dept dept);

}
