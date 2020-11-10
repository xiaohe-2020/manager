/**************************************************************************
 * Project Description
 *
 * [File]
 * SpringUtils.java
 *
 * Copyright (C) 2010 北京盛臣技术有限公司. 
 * All Rights Reserved.
 * 
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF CSCTEK CORPORATION.
 * 
 * The copyright notice above does not evidence any
 * actual or intended publication of such source code.
 * 
 * [Description]
 * <Class Description>
 * 本类定义Spring操作类。
 * 
 * [Author]
 * 北京盛臣技术有限公司
 * [History]
 * date                    editor                      modification
 * 2010/05/10         北京盛臣技术有限公司                     创建
 **************************************************************************/
package com.example.test.comm.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class SpringUtils implements ApplicationContextAware {

	private ApplicationContext context = null;
	
	private static SpringUtils self;
	
	@PostConstruct
    public void init() {
		self = this;
    }

	@Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

	public static Object getBean(String name){
        return self.context.getBean(name);
    }

	public static <T> T getBean(Class<T> clazz){
        return self.context.getBean(clazz);
    }
}
