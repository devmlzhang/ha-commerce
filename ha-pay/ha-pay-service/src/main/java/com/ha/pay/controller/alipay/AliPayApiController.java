package com.ha.pay.controller.alipay;

import com.jpay.alipay.AliPayApiConfig;

/**
 * <p>
 *    阿里云支付配置Controller
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/5
 */
public abstract class AliPayApiController{
	public abstract  AliPayApiConfig getApiConfig();
}