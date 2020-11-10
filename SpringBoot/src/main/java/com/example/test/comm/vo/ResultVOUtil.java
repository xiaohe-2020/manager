package com.example.test.comm.vo;


public class ResultVOUtil {

	public static <T> ResultVO<T> success(T object) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setCode(ErrorEnum.SUCCESS.getCode());
        resultVO.setMsg(ErrorEnum.SUCCESS.getMsg());
        return resultVO;
    }

    public static <T> ResultVO<T> success() {
        return success(null);
    }

    public static <T> ResultVO<T> error(ErrorEnum errorEnum) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(errorEnum.getCode());
        resultVO.setMsg(errorEnum.getMsg());
        return resultVO;
    }
    
    public static <T> ResultVO<T> error(ErrorEnum errorEnum, String customMsg, T data) {
    	ResultVO<T> resultVO = new ResultVO<>();
    	resultVO.setCode(errorEnum.getCode());
    	resultVO.setMsg(errorEnum.getMsg() + "，" + customMsg);
    	resultVO.setData(data);
    	return resultVO;
    }    
    
    public static <T> ResultVO<T> error(ErrorEnum errorEnum, String customMsg) {
    	ResultVO<T> resultVO = new ResultVO<>();
    	resultVO.setCode(errorEnum.getCode());
    	resultVO.setMsg(errorEnum.getMsg() + "，" + customMsg);
    	return resultVO;
    }
    
    public static <T> ResultVO<T> error(ErrorEnum errorEnum, T data) {
    	ResultVO<T> resultVO = new ResultVO<>();
    	resultVO.setCode(errorEnum.getCode());
    	resultVO.setMsg(errorEnum.getMsg());
    	resultVO.setData(data);
    	return resultVO;
    }
    
    public static <T> ResultVO<T> error(T object) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setCode(ErrorEnum.SYSTEM_ERROR.getCode());
        return resultVO;
    }
    
    
}
