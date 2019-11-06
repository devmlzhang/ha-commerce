package com.ha.common.exception;

import com.ha.common.enums.ResultEnum;

/**
 * <p>
 *    自定义异常
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/14
 */
public class ExceptionCast {

    public static void cast(ResultEnum resultCode){
        throw new CustomException(resultCode);
    }
}
