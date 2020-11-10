package com.example.test.comm.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("all")
public class BaseServiceImpl<T extends BaseEntity> extends BaseTools implements BaseService<T> {

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	@Autowired
	protected HttpSession session;

	@Autowired
	private BaseMapper<T> mapper;

	@Override
	public T get(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public T getUnDeleted(Long id) {
		T entity = mapper.selectByPrimaryKey(id);
		return entity;
	}

	@Override
	public int save(T t) {
		Long id = t.getId();
		if (id == null || id.intValue() == 0) {
			return mapper.insertSelective(t);
		} else {
			return mapper.updateByPrimaryKeySelective(t);
		}
	}

	@Override
	public int insertList(List<T> list) {
		if (null == list || list.isEmpty()) {
			return 0;
		}
		return mapper.insertList(list);
	}

	@Override
	public List<T> findByIds(String ids) {
		return mapper.selectByIds(ids);
	}

	@Override
	public List<T> findAll() {
		return mapper.selectAll();
	}

}
