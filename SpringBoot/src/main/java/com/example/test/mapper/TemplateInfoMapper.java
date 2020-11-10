package com.example.test.mapper;

import com.example.test.comm.base.BaseMapper;
import com.example.test.entity.TemplateInfoEntity;
import com.example.test.mapper.sql.TemplateInfoSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface TemplateInfoMapper extends BaseMapper<TemplateInfoEntity> {

    //更新模板名称
    @UpdateProvider(type = TemplateInfoSqlProvider.class, method = "updateById")
    int updateById(Long id);



}
