package com.example.test.service;

import com.example.test.comm.base.BaseService;
import com.example.test.comm.vo.ResultVO;
import com.example.test.entity.TemplateInfoEntity;

public interface TemplateService  extends BaseService<TemplateInfoEntity> {

    public ResultVO<?> doUpdate();

}
