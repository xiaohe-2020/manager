/**************************************************************************
 * Project Description
 *
 * [File]
 * DwpApplicationDTO.java
 *
 * Copyright (C) 2019 网拍天下. 
 * All Rights Reserved.
 * 
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF VIWOR CORPORATION.
 * 
 * The copyright notice above does not evidence any
 * actual or intended publication of such source code.
 * 
 * [Description]
 * <Class Description>
 * 本类定义dwp应用管理响应类。
 * 
 * [Author]
 * 网拍天下
 * [History]
 * date						editor						modification
 * 2020/08/11				网拍天下							创建
 **************************************************************************/
package com.example.test.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 本类定义dwp应用管理响应类。
 * 
 * @author WangShuai
 * @version 1.0 2020/08/11
 */
@SuppressWarnings("all")
@ApiModel(value = "请求对象")
public class TestDTO {

	@ApiModelProperty(value = "记录主键ID", required = false)
	private Long id;

	@ApiModelProperty(value = "应用名称", required = false)
	private String applicationName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
}