package com.example.test.comm.base;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface BaseMapper<T extends BaseEntity> extends Mapper<T>, SelectByIdsMapper<T>, InsertListMapper<T> {
	
	
}
