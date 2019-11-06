package com.ha.user.exception;


import com.ha.common.enums.ResultEnum;
import com.ha.common.response.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * <p>
 *  全局异常处理
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/14
 */
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult error(Exception e){
        e.printStackTrace();
        return new ResponseResult(ResultEnum.RESULT_ERROR.getCode(),ResultEnum.RESULT_ERROR.getMsg()+":"+e.getMessage());
    }
}
