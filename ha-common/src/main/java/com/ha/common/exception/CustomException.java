package com.ha.common.exception;


import com.ha.common.enums.ResultEnum;

/**
 * <p>
 *    自定义异常类型
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/14
 */
public class CustomException extends RuntimeException {

    //错误代码
    ResultEnum resultCode;

    public CustomException(ResultEnum resultCode){
        this.resultCode = resultCode;
    }
    public ResultEnum getResultCode(){
        return resultCode;
    }


}
