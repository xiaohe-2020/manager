package com.example.test.service.impl;

import com.example.test.comm.base.BaseServiceImpl;
import com.example.test.comm.vo.ResultVO;
import com.example.test.comm.vo.ResultVOUtil;
import com.example.test.eache.UserEacheService;
import com.example.test.entity.TemplateInfoEntity;
import com.example.test.mapper.TemplateInfoMapper;
import com.example.test.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@Service
public class TemplateServiceImpl extends BaseServiceImpl<TemplateInfoEntity> implements TemplateService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    TemplateInfoMapper mapper;

    @Autowired
    UserEacheService userEacheService;

    @Override
    public ResultVO<?> doUpdate() {
        TemplateInfoEntity infoEntity = new TemplateInfoEntity();
        infoEntity.setTemplateName("测试redis新增数据");
        userEacheService.putUserCache(3L,infoEntity);
        log.info("缓存数据成功！");
        if(9/0 == 0){
            return null;
        }
        return ResultVOUtil.success();
    }
}
