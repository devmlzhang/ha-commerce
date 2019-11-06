package com.ha.common.exception;

import com.google.common.collect.ImmutableMap;
import com.ha.common.enums.ResultEnum;
import com.ha.common.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *    统一异常捕获类
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/14
 */
@ControllerAdvice//控制器增强
public class ExceptionCatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    //定义map，配置异常类型所对应的错误代码
    private static ImmutableMap<Class<? extends Throwable>, ResultEnum> EXCEPTIONS;
    //定义map的builder对象，去构建ImmutableMap
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultEnum> builder = ImmutableMap.builder();

    //捕获CustomException此类异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException customException){
        customException.printStackTrace();
        //记录日志
        LOGGER.error("catch exception:{}",customException.getMessage());
        ResultEnum resultEnum = customException.getResultCode();
        return new ResponseResult(resultEnum);
    }
    //捕获Exception此类异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception exception){
        exception.printStackTrace();
        //记录日志
        LOGGER.error("catch exception:{}",exception.getMessage());
        if(EXCEPTIONS == null){
            EXCEPTIONS = builder.build();//EXCEPTIONS构建成功
        }
        //从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到给用户响应99999异常
        ResultEnum resultCode = EXCEPTIONS.get(exception.getClass());
        if(resultCode !=null){
            return new ResponseResult(resultCode);
        }else{
            //返回99999异常
            return new ResponseResult(ResultEnum.SERVER_ERROR);
        }

    }

    static {
        //定义异常类型所对应的错误代码
        builder.put(HttpMessageNotReadableException.class,ResultEnum.PARAM_ERROR);
    }
}
