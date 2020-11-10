package com.example.test.Thread;

import com.example.test.comm.config.ServiceConfig;
import com.example.test.comm.utils.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TaskThread extends Thread{
	
	private static Log log = LogFactory.getLog(TaskThread.class);

	
	public static TestThread testThread = new TestThread();
	

	public void run(){
		ServiceConfig serviceConfig = SpringUtils.getBean(ServiceConfig.class);
		try {
			
			//启动测试线程
			Boolean testTreadFlg = serviceConfig.isTestthreadFlag();
			log.info("是否启动任务到期提醒线程【"+testTreadFlg+"】");
			if (testTreadFlg) {
				
				log.info("┌─────────────────────────────────────────────");
				log.info("|    启动任务到期提醒线程......");
				String testWaitingSecond = serviceConfig.getTestStartwaitingsecond();
				String testLoopTimerSecond = serviceConfig.getTestLooptimersecond();
				String testTotalLoopTimerSecond = serviceConfig.getTestTotallooptimersecond();
				
				Long taskreminderStartwaitingsecond = Long.parseLong(testWaitingSecond);
				Long taskreminderLooptimersecond = Long.parseLong(testLoopTimerSecond);
				Long taskreminderTotallooptimersecond = Long.parseLong(testTotalLoopTimerSecond);
				log.info("|    线程启动时等待时间(秒):"+testWaitingSecond);
				log.info("|    循环周期(秒):"+testLoopTimerSecond);
				log.info("|    最大等待时间(秒):"+testTotalLoopTimerSecond);
				log.info("└─────────────────────────────────────────────");
				if(null == testThread || !testThread.isAlive()){
					testThread = new TestThread();
					testThread.setRunFlag(true);
					testThread.init("demo-测试线程",taskreminderStartwaitingsecond, taskreminderLooptimersecond, taskreminderTotallooptimersecond);
				}
				testThread.setDaemon(true);
				testThread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
