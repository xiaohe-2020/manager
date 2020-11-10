package com.example.test.comm.utils;


import com.example.test.Thread.TaskThread;
import com.example.test.Thread.TestThread;
import com.example.test.comm.config.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger log = LoggerFactory.getLogger(StartupListener.class);

	private static boolean isInit = false;

	@Autowired
	private ServiceConfig serviceConfig;

	@SuppressWarnings("static-access")
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if(isInit) {
			log.info("启动初始化已执行，不重复执行");
			return;
		}
		isInit = true;

		//启动线程
		Boolean taskThreadFlag = serviceConfig.isTaskthreadFlag();
		log.info("是否启动相关线程【"+taskThreadFlag+"】");
		if (taskThreadFlag) {
			TaskThread taskThread = new TaskThread();
			log.info("*****************************");
			log.info("*        启动任务相关线程                 *");
			log.info("*****************************");
			taskThread.start();
		}

	}
}
