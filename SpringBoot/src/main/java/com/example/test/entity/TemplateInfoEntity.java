package com.example.test.entity;

import com.example.test.comm.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("all")
@Entity
@Table(name = "template_info")
public class TemplateInfoEntity extends BaseEntity {

    @ApiModelProperty(value = "模板名称", required = false)
    private String templateName;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

}
