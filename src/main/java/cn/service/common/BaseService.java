package cn.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public abstract class BaseService<T>{

	@Autowired
	protected Mapper<T> mapper;

	public Mapper<T> getMapper() {
		return mapper;
	}

	public List<T> selectAll() {
		return mapper.selectAll();
	}

	public T selectByKey(Object key) {
		return mapper.selectByPrimaryKey(key);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int save(T entity) {
		return mapper.insert(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int delete(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int batchDelete(List<String> list, String property, Class<T> clazz) {
		Example example = new Example(clazz);
		example.createCriteria().andIn(property, list);
		return this.mapper.deleteByExample(example);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int updateAll(T entity) {
		return mapper.updateByPrimaryKey(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int updateNotNull(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	public List<T> selectByExample(Object example) {
		return mapper.selectByExample(example);
	}
}
