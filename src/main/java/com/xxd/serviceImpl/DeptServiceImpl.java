package com.xxd.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xxd.service.DeptService;
import com.xxd.util.controller.TreeUtils;

import cn.service.common.BaseService;
import tk.mybatis.mapper.entity.Example;

import com.xxd.mapper.DeptMapper;
import com.xxd.pojo.Dept;
import com.xxd.pojo.Tree;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl extends BaseService<Dept>  implements DeptService {

	@Autowired
	private DeptMapper deptMapper;

	@Override
	public Tree<Dept> getDeptTree() {
		List<Tree<Dept>> trees = new ArrayList<Tree<Dept>>();
		List<Dept> depts = this.findAllDepts(new Dept());
		for (Dept dept : depts) {
			Tree<Dept> tree = new Tree<Dept>();
			tree.setId(dept.getDeptId().toString());
			tree.setParentId(dept.getParentId().toString());
			tree.setText(dept.getDeptName());
			trees.add(tree);
		}
		Tree<Dept> t = TreeUtils.build(trees);
		return t;
	}

	@Override
	public List<Dept> findAllDepts(Dept dept) {
		try {
			Example example = new Example(Dept.class);
			if (StringUtils.isNotBlank(dept.getDeptName())) {
				example.createCriteria().andCondition("dept_name=", dept.getDeptName());
			}
			example.setOrderByClause("dept_id");
			return this.deptMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Dept>();
		}

	}

}
