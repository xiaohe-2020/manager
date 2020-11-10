package com.example.test.comm.vo;


public enum ErrorEnum {

	/*
	 * actionName_methodName_errorDeac 1001-9999
	 * 多method通用，去掉methodName
	 * 1、全局错误码，0-99开始
	 * 2、多action通用101-999
	 */
	SUCCESS(0, "成功"),
	SYSTEM_ERROR(-1, "网络繁忙,请稍后再试"),

	;


	private Integer code;
	private String msg;

	private ErrorEnum(Integer code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}

}


