package com.ha.goods.advice;


import com.ha.common.enums.ResultEnum;
import com.ha.common.exception.ExceptionCatch;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * <p>
 *    自定义的异常类，其中定义异常类型所对应的错误代码
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/16
 */
@ControllerAdvice//控制器增强
public class CustomExceptionCatch extends ExceptionCatch {
    static {
        builder.put(AccessDeniedException.class, ResultEnum.UNAUTHORISE);
    }
}
