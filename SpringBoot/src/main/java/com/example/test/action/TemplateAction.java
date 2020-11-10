package com.example.test.action;


import com.example.test.comm.vo.ResultVO;
import com.example.test.service.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template")
@Api(tags = "TemplateAction", description = "模板管理")
public class TemplateAction {

    // 日志输出
    private static Logger log = LoggerFactory.getLogger(TemplateAction.class);

    @Autowired
    TemplateService service;

    @ApiOperation(value = "更新模板名称")
    @PostMapping("/update")
    public ResultVO<?> updateTemplateInfo(){
        return  service.doUpdate();
    }
}
