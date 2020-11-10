package com.example.test.mapper.sql;

import org.apache.ibatis.annotations.Param;

public class TemplateInfoSqlProvider {

    public String updateById(@Param("id") Long id) {

        StringBuilder sql = new StringBuilder();

        sql.append(" UPDATE template_info t SET t.template_name = '模板H' where id = #{id} ");

        return sql.toString();
    }

}
