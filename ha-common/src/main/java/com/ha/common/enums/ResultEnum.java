package com.ha.common.enums;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 *    枚举
 * </p>
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultEnum {
	/**成功处理*/
	RESULT_SUCCESS(200,"处理成功"),
	/**处理业务失败*/
	RESULT_ERROR(500,"处理错误"),
	/**参数验证失败*/
	PARAM_ERROR(501,"参数错误"),
	/**暂未登录或token已经过期 **/
	UNAUTHORIZED(401, "暂未登录或token已经过期"),
	/**太多请求 **/
	TOO_MANY_REQUESTS(429, "太多请求，请稍后再试！"),
	/**没有相关权限 **/
	FORBIDDEN(403, "没有相关权限"),
	/**账号密码错误 **/
	USERNAME_PASSWORD_ERROR(701, "账号密码错误"),
	/**触发熔断 **/
	BREAKING(430, "触发熔断"),
	/** 抱歉，系统繁忙，请稍后重试**/
	SERVER_ERROR(503,"抱歉，系统繁忙，请稍后重试！"),
	
	/**mq消息处理*/
	SEND_ERROR(601,"发送失败"),
	CONSUME_ERROR(602,"发送失败"),

	/** 登录模块 **/
	AUTH_USERNAME_NONE(23001,"请输入账号！"),
	AUTH_PASSWORD_NONE(23002,"请输入密码！"),
	AUTH_VERIFYCODE_NONE(23003,"请输入验证码！"),
	AUTH_ACCOUNT_NOTEXISTS(23004,"账号不存在！"),
	AUTH_CREDENTIAL_ERROR(23005,"账号或密码错误！"),
	AUTH_LOGIN_ERROR(23006,"登陆过程出现异常请尝试重新操作！"),
	AUTH_LOGIN_APPLYTOKEN_FAIL(23007,"申请令牌失败！"),
	AUTH_LOGIN_TOKEN_SAVEFAIL(23008,"存储令牌失败！"),
	AUTH_LOGOUT_FAIL(23009,"退出失败！"),

	UNAUTHENTICATED(10001,"此操作需要登陆系统！"),
	UNAUTHORISE(10002,"权限不足，无权操作！");


	/**返回编码*/
	private int code;
	/**返回描述*/
	private String msg;

	private static final ImmutableMap<Integer, ResultEnum> CACHE;

	static {
		final ImmutableMap.Builder<Integer, ResultEnum> builder = ImmutableMap.builder();
		for (ResultEnum commonCode : values()) {
			builder.put(commonCode.code(), commonCode);
		}
		CACHE = builder.build();
	}


	public int code() {
		return code;
	}

	public String msg() {
		return msg;
	}

}
