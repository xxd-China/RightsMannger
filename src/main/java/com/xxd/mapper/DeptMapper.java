package com.xxd.mapper;

import java.util.List;
import com.xxd.pojo.Dept;

import cn.mapper.common.MyMapper;

public interface DeptMapper extends MyMapper<Dept>{
	
	List<Dept> findAllDepts(Dept dept);
	
}