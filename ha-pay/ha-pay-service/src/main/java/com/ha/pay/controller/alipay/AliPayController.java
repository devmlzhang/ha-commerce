package com.ha.pay.controller.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.response.AlipayFundAuthOrderFreezeResponse;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.ha.pay.properties.AliPayProperties;
import com.jpay.alipay.AliPayApi;
import com.jpay.alipay.AliPayApiConfig;
import com.jpay.alipay.AliPayApiConfigKit;
import com.jpay.util.StringUtils;
import com.jpay.vo.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 *    阿里支付
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/5
 */
@Controller
@RequestMapping("/alipay")
public class AliPayController extends AliPayApiController {
    private static final Logger log = LoggerFactory.getLogger(AliPayController.class);

	@Autowired
	private AliPayProperties aliPayProperties;

	private AjaxResult result = new AjaxResult();

	@Override
	public AliPayApiConfig getApiConfig() {
		return AliPayApiConfig.New()
				.setAppId(aliPayProperties.getAppId())
				.setAlipayPublicKey(aliPayProperties.getPublicKey())
				.setCharset("UTF-8")
				.setPrivateKey(aliPayProperties.getPrivateKey())
				.setServiceUrl(aliPayProperties.getServerUrl())
				.setSignType("RSA2")
				.build();
	}
	
	@RequestMapping("")
	@ResponseBody
	public String index() {
		return "欢迎使用支付宝支付";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		String charset = AliPayApiConfigKit.getAliPayApiConfig().getCharset();
		log.info("charset>"+charset);
		return aliPayProperties.toString();
	}


	/**
	 * app支付
	 */
	@RequestMapping(value = "/appPay")
	@ResponseBody
	public AjaxResult appPay() {
		try {
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setBody("我是测试数据");
			model.setSubject("App支付测试");
			model.setOutTradeNo(StringUtils.getOutTradeNo());
			model.setTimeoutExpress("30m");
			model.setTotalAmount("0.01");
			model.setPassbackParams("callback params");
			model.setProductCode("QUICK_MSECURITY_PAY");
			String orderInfo = AliPayApi.appPayToResponse(model, aliPayProperties.getDomain() + "/alipay/notify_url").getBody();
			result.success(orderInfo);
		} catch (AlipayApiException e) {
			e.printStackTrace();
			result.addError("system error:"+e.getMessage());
		}
		return result;
	}
	
	/**
	 *
	 * 手机网站支付
	 */
	@RequestMapping(value = "/wapPay")
	@ResponseBody
	public void wapPay(HttpServletResponse response) {
		String body = "我是测试数据";
		String subject = "Wap支付测试";
		String totalAmount = "0.1";
		String passbackParams = "1";
		String returnUrl = aliPayProperties.getDomain() + "/alipay/return_url";
		String notifyUrl = aliPayProperties.getDomain() + "/alipay/notify_url";

		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		model.setBody(body);
		model.setSubject(subject);
		model.setTotalAmount(totalAmount);
		model.setPassbackParams(passbackParams);
		String outTradeNo = StringUtils.getOutTradeNo();
		System.out.println("wap outTradeNo>"+outTradeNo);
		model.setOutTradeNo(outTradeNo);
		model.setProductCode("QUICK_WAP_PAY");

		try {
			AliPayApi.wapPay(response, model, returnUrl, notifyUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * PC支付
	 */
	@RequestMapping(value = "/pcPay")
	@ResponseBody
	public void pcPay(HttpServletResponse response){
		try {
			String totalAmount = "0.1";
			String outTradeNo =StringUtils.getOutTradeNo();
			log.info("pc outTradeNo>"+outTradeNo);
			
			String returnUrl = aliPayProperties.getDomain() + "/alipay/return_url";
			String notifyUrl = aliPayProperties.getDomain() + "/alipay/notify_url";
			AlipayTradePagePayModel model = new AlipayTradePagePayModel();
			
			model.setOutTradeNo(outTradeNo);
			model.setProductCode("FAST_INSTANT_TRADE_PAY");
			model.setTotalAmount(totalAmount);
			model.setSubject("PC支付测试");
			model.setBody("PC支付测试");
			model.setPassbackParams("passback_params");
			//花呗分期相关的设置
			/**
			 * 测试环境不支持花呗分期的测试
			 * hb_fq_num代表花呗分期数，仅支持传入3、6、12，其他期数暂不支持，传入会报错；
			 * hb_fq_seller_percent代表卖家承担收费比例，商家承担手续费传入100，用户承担手续费传入0，仅支持传入100、0两种，其他比例暂不支持，传入会报错。
			 */
			ExtendParams extendParams = new ExtendParams();
			extendParams.setHbFqNum("3");
			extendParams.setHbFqSellerPercent("0");
			model.setExtendParams(extendParams);
			
			AliPayApi.tradePage(response,model , notifyUrl, returnUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}




	/**
	 * 扫码支付
	 */
	@RequestMapping(value ="/tradePrecreatePay")
	@ResponseBody
	public String tradePrecreatePay() {
		String subject = "支付宝扫码支付测试";
		String totalAmount = "0.1";
		String storeId = "123";
		String notifyUrl = aliPayProperties.getDomain() + "/alipay/notify_url";

		AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
		model.setSubject(subject);
		model.setTotalAmount(totalAmount);
		model.setStoreId(storeId);
		model.setTimeoutExpress("5m");
		model.setOutTradeNo(StringUtils.getOutTradeNo());
		try {
			String resultStr = AliPayApi.tradePrecreatePayToResponse(model, notifyUrl).getBody();
			JSONObject jsonObject = JSONObject.parseObject(resultStr);
			return jsonObject.getJSONObject("alipay_trade_precreate_response").getString("qr_code");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 单笔转账到支付宝账户
	 * https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.54Ty29&
	 * treeId=193&articleId=106236&docType=1
	 */
	@RequestMapping(value = "/transfer")
	@ResponseBody
	public boolean transfer() {
		boolean isSuccess = false;
		String total_amount = "0.1";
		AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
		model.setOutBizNo(StringUtils.getOutTradeNo());
		model.setPayeeType("ALIPAY_LOGONID");
		model.setPayeeAccount("17608501223");
		model.setPayeeRealName("张明亮");
		model.setAmount(total_amount);
		model.setPayerShowName("贵阳xx有限公司");
		model.setPayerRealName("贵阳xx有限公司");
		model.setRemark("javen测试单笔转账到支付宝");

		try {
			isSuccess = AliPayApi.transfer(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	/**
	 * 资金授权冻结接口
	 */
	@RequestMapping(value = "/authOrderFreeze")
	@ResponseBody
	public AlipayFundAuthOrderFreezeResponse authOrderFreeze(@RequestParam("auth_code") String authCode){
		try {
			AlipayFundAuthOrderFreezeModel model = new AlipayFundAuthOrderFreezeModel();
			model.setOutOrderNo(StringUtils.getOutTradeNo());
			model.setOutRequestNo(StringUtils.getOutTradeNo());
			model.setAuthCode(authCode);
			model.setAuthCodeType("bar_code");
			model.setOrderTitle("资金授权冻结");
			model.setAmount("36");
//			model.setPayTimeout("");
			model.setProductCode("PRE_AUTH");
			
			AlipayFundAuthOrderFreezeResponse response = AliPayApi.authOrderFreezeToResponse(model);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	


	/**
	 * 退款
	 */
	@RequestMapping(value = "/tradeRefund")
	@ResponseBody
	public String tradeRefund() {
		try {
			AlipayTradeRefundModel model = new AlipayTradeRefundModel();
			model.setOutTradeNo("091014465115680");
			model.setTradeNo("2019091022001433780557071799");
			model.setRefundAmount("0.1");
			model.setRefundReason("正常退款");
			return AliPayApi.tradeRefundToResponse(model).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 交易查询
	 */
	@RequestMapping(value = "/tradeQuery")
	@ResponseBody
	public boolean tradeQuery() {
		boolean isSuccess = false;
		try {
			AlipayTradeQueryModel model = new AlipayTradeQueryModel();
			model.setOutTradeNo("091014465115680");
			model.setTradeNo("2019091022001433780557071799");

			isSuccess = AliPayApi.tradeQueryToResponse(model).isSuccess();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	@RequestMapping(value = "/tradeQueryByStr")
	@ResponseBody
	public String  tradeQueryByStr(@RequestParam("out_trade_no") String out_trade_no) {
		AlipayTradeQueryModel model = new AlipayTradeQueryModel();
		model.setOutTradeNo(out_trade_no);

		try {
			return AliPayApi.tradeQueryToResponse(model).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 创建订单
	 * {"alipay_trade_create_response":{"code":"10000","msg":"Success","out_trade_no":"081014283315033","trade_no":"2017081021001004200200274066"},"sign":"ZagfFZntf0loojZzdrBNnHhenhyRrsXwHLBNt1Z/dBbx7cF1o7SZQrzNjRHHmVypHKuCmYifikZIqbNNrFJauSuhT4MQkBJE+YGPDtHqDf4Ajdsv3JEyAM3TR/Xm5gUOpzCY7w+RZzkHevsTd4cjKeGM54GBh0hQH/gSyhs4pEN3lRWopqcKkrkOGZPcmunkbrUAF7+AhKGUpK+AqDw4xmKFuVChDKaRdnhM6/yVsezJFXzlQeVgFjbfiWqULxBXq1gqicntyUxvRygKA+5zDTqE5Jj3XRDjVFIDBeOBAnM+u03fUP489wV5V5apyI449RWeybLg08Wo+jUmeOuXOA=="}
	 */
	@RequestMapping(value = "/tradeCreate")
	@ResponseBody
	public String tradeCreate(@RequestParam("out_trade_no") String outTradeNo){

		String notifyUrl = aliPayProperties.getDomain()+ "/alipay/notify_url";

		AlipayTradeCreateModel model = new AlipayTradeCreateModel();
		model.setOutTradeNo(outTradeNo);
		model.setTotalAmount("88.88");
		model.setBody("Body");
		model.setSubject("测试统一收单交易创建接口");
		model.setBuyerLogonId("abpkvd0206@sandbox.com");//买家支付宝账号，和buyer_id不能同时为空
		try {
			AlipayTradeCreateResponse response = AliPayApi.tradeCreateToResponse(model, notifyUrl);
			return response.getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 撤销订单
	 */
	@RequestMapping(value = "/tradeCancel")
	@ResponseBody
	public boolean tradeCancel() {
		boolean isSuccess = false;
		try {
			AlipayTradeCancelModel model = new AlipayTradeCancelModel();
			model.setOutTradeNo("081014283315033");
			model.setTradeNo("2017081021001004200200274066");

			isSuccess = AliPayApi.tradeCancelToResponse(model).isSuccess();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 关闭订单
	 */
	@RequestMapping(value = "/tradeClose")
	@ResponseBody
	public String tradeClose(@RequestParam("out_trade_no") String outTradeNo, @RequestParam("trade_no") String tradeNo){
		try {
			AlipayTradeCloseModel model = new AlipayTradeCloseModel();
			model.setOutTradeNo(outTradeNo);

			model.setTradeNo(tradeNo);

			return AliPayApi.tradeCloseToResponse(model).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 结算
	 */
	@RequestMapping(value = "/tradeOrderSettle")
	@ResponseBody
	public String  tradeOrderSettle(@RequestParam("trade_no") String tradeNo){
		try {
			AlipayTradeOrderSettleModel model = new AlipayTradeOrderSettleModel();
			model.setOutRequestNo(StringUtils.getOutTradeNo());
			model.setTradeNo(tradeNo);

			return AliPayApi.tradeOrderSettleToResponse(model ).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取应用授权URL并授权
	 */
	@RequestMapping(value = "/toOauth")
	@ResponseBody
	public void toOauth(HttpServletResponse response) {
		try {
			String redirectUri = aliPayProperties.getDomain()+ "/alipay/redirect_uri";
			String oauth2Url = AliPayApi.getOauth2Url(aliPayProperties.getAppId(), redirectUri);
			response.sendRedirect(oauth2Url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 应用授权回调
	 */
	@RequestMapping(value = "/redirect_uri")
	@ResponseBody
	public String redirect_uri(@RequestParam("app_id") String app_id, @RequestParam("app_auth_code") String app_auth_code) {
		try {
			System.out.println("app_id:"+app_id);
			System.out.println("app_auth_code:"+app_auth_code);
			//使用app_auth_code换取app_auth_token
			AlipayOpenAuthTokenAppModel model = new AlipayOpenAuthTokenAppModel();
			model.setGrantType("authorization_code");
			model.setCode(app_auth_code);
			return  AliPayApi.openAuthTokenAppToResponse(model).getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询授权信息
	 */
	@RequestMapping(value = "/openAuthTokenAppQuery")
	@ResponseBody
	public String openAuthTokenAppQuery(@RequestParam("app_auth_token") String app_auth_token) {
		try {
			AlipayOpenAuthTokenAppQueryModel model = new AlipayOpenAuthTokenAppQueryModel();
			model.setAppAuthToken(app_auth_token);
			return  AliPayApi.openAuthTokenAppQueryToResponse(model).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}


	@RequestMapping(value = "/return_url")
	@ResponseBody
	public String return_url(HttpServletRequest request) {
		try {
			// 获取支付宝GET过来反馈信息
			Map<String, String> map = AliPayApi.toMap(request);
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " = " + entry.getValue());
			}

			boolean verify_result = AlipaySignature.rsaCheckV1(map, aliPayProperties.getPublicKey(), "UTF-8",
					"RSA2");

			if (verify_result) {// 验证成功
				// TODO 请在这里加上商户的业务逻辑程序代码
				System.out.println("return_url 验证成功");

				return "success";
			} else {
				System.out.println("return_url 验证失败");
				// TODO
				return "failure";
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return "failure";
		}
	}



	@RequestMapping(value = "/notify_url")
	@ResponseBody
	public String  notify_url(HttpServletRequest request) {
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = AliPayApi.toMap(request);

			for (Map.Entry<String, String> entry : params.entrySet()) {
				System.out.println(entry.getKey() + " = " + entry.getValue());
			}

			boolean verify_result = AlipaySignature.rsaCheckV1(params, aliPayProperties.getPublicKey(), "UTF-8",
					"RSA2");

			if (verify_result) {// 验证成功
				// TODO 请在这里加上商户的业务逻辑程序代码 异步通知可能出现订单重复通知 需要做去重处理
				System.out.println("notify_url 验证成功succcess");
				return "success";
			} else {
				System.out.println("notify_url 验证失败");
				// TODO
				return "failure";
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return "failure";
		}
	}
}
