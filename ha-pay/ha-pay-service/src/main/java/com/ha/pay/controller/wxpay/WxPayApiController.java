package com.ha.pay.controller.wxpay;

import com.jpay.weixin.api.WxPayApiConfig;

/**
 * <p>
 *    微信支付配置Controller
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/5
 */
public abstract class WxPayApiController{
	public abstract WxPayApiConfig getApiConfig();
}