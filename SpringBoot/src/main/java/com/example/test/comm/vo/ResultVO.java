package com.example.test.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "响应体")
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 6479902640929042084L;

    /** 错误码. */
    @ApiModelProperty(value = "返回码" , required = true)
    private Integer code;

    /** 提示信息. */
    @ApiModelProperty(value = "返回信息" , required = true)
    private String msg;

    /** 具体内容. */
    @ApiModelProperty(value = "返回结果" , required = true)
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }




}