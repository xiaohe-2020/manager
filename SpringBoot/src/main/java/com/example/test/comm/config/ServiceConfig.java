package com.example.test.comm.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "service")
@Component
public class ServiceConfig {

	//是否启动任务相关线程
	private boolean testthreadFlag;
	//是否启动测试线程
	private boolean taskthreadFlag;
	//线程启动时等待时间(秒)
	private String testStartwaitingsecond;
	//循环周期(秒)
	private String  testLooptimersecond;
	// 最大等待时间(秒)
	private String  testTotallooptimersecond;

	public boolean isTestthreadFlag() {
		return testthreadFlag;
	}

	public void setTestthreadFlag(boolean testthreadFlag) {
		this.testthreadFlag = testthreadFlag;
	}

	public boolean isTaskthreadFlag() {
		return taskthreadFlag;
	}

	public void setTaskthreadFlag(boolean taskthreadFlag) {
		this.taskthreadFlag = taskthreadFlag;
	}

	public String getTestStartwaitingsecond() {
		return testStartwaitingsecond;
	}

	public void setTestStartwaitingsecond(String testStartwaitingsecond) {
		this.testStartwaitingsecond = testStartwaitingsecond;
	}

	public String getTestLooptimersecond() {
		return testLooptimersecond;
	}

	public void setTestLooptimersecond(String testLooptimersecond) {
		this.testLooptimersecond = testLooptimersecond;
	}

	public String getTestTotallooptimersecond() {
		return testTotallooptimersecond;
	}

	public void setTestTotallooptimersecond(String testTotallooptimersecond) {
		this.testTotallooptimersecond = testTotallooptimersecond;
	}
}
