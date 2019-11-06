package com.ha.common.response;


import com.ha.common.enums.ResultEnum;

/**
 * <p>
 *   公用返回结果类
 * </p>
 *
 * @author ML.Zhang
 * @since  2019-05-23
 */
public class ResponseResult<T> {
	
	/**返回错误编码*/
	private int code;
	/**返回错误信息*/
	private String msg;
	/**返回结果*/
	private T result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public ResponseResult() {
		
	}
	
	public ResponseResult(int code, String msg, T result) {
		super();
		this.code = code;
		this.msg = msg;
		this.result = result;
	}
	
	public ResponseResult(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResponseResult(ResultEnum resultEnum) {
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMsg();
	}

	/**处理成功返回 无返回内容*/
	public static ResponseResult successResult(){
		return new ResponseResult(ResultEnum.RESULT_SUCCESS.getCode(),
								  ResultEnum.RESULT_SUCCESS.getMsg()
								  );
	}

	/**
	 * 处理成功，返回提示错误
	 * @param msg
	 * @return
	 */
	public static ResponseResult successResult(String msg){
		return new ResponseResult(ResultEnum.RESULT_SUCCESS.getCode(), msg);
	}
	/**处理成功返回*/
	public static ResponseResult successResult(Object obj){
		return new ResponseResult(ResultEnum.RESULT_SUCCESS.getCode(),
								  ResultEnum.RESULT_SUCCESS.getMsg(),
								  obj);
	}
	/**处理成功返回对象**/
	public static ResponseResult successResult(String message, Object obj){
		return new ResponseResult(ResultEnum.RESULT_SUCCESS.getCode(),
				ResultEnum.RESULT_SUCCESS.getMsg(),
				obj);
	}

	/**处理失败返回*/
	public static ResponseResult exceptionResult(ResultEnum resultEnum){
		return new ResponseResult(resultEnum);
	}

	/**处理失败返回*/
	public static ResponseResult errorResult(Object obj){
		return new ResponseResult(ResultEnum.RESULT_ERROR.getCode(),
								  ResultEnum.RESULT_ERROR.getMsg(),
								  obj);
	}
	/**处理失败加提示错误**/
	public static ResponseResult errorResult(String errmsg){
		return new ResponseResult(ResultEnum.RESULT_ERROR.getCode(),
				errmsg);
	}
	/**处理失败返回  无返回内容*/
	public static ResponseResult errorResult(){
		return new ResponseResult(ResultEnum.RESULT_ERROR.getCode(),
								  ResultEnum.RESULT_ERROR.getMsg()
								  );
	}

	/** 没有权限**/
	public static ResponseResult unauthorized(Object obj ){
		return new ResponseResult(ResultEnum.UNAUTHORIZED.getCode(),
				ResultEnum.UNAUTHORIZED.getMsg(),
				obj
		);
	}


	/** 请求太多**/
	public static ResponseResult tooManyRequests(Object obj ){
		return new ResponseResult(ResultEnum.TOO_MANY_REQUESTS.getCode(),
				ResultEnum.TOO_MANY_REQUESTS.getMsg(),
				obj
		);
	}

	/** 请求太多**/
	public static ResponseResult tooManyRequests(){
		return new ResponseResult(ResultEnum.TOO_MANY_REQUESTS.getCode(),
				ResultEnum.TOO_MANY_REQUESTS.getMsg()
		);
	}

	/** 未授权**/
	public static ResponseResult forbidden(Object obj ){
		return new ResponseResult(ResultEnum.FORBIDDEN.getCode(),
				ResultEnum.FORBIDDEN.getMsg(),
				obj
		);
	}
	
	/**参数错误返回*/
	public static ResponseResult paramsErrorResult(){
		return new ResponseResult(ResultEnum.PARAM_ERROR.getCode(),
								  ResultEnum.PARAM_ERROR.getMsg()
								  );
	}
	/**参数错误返回*/
	public static ResponseResult paramsErrorResult(String errmsg){
		return new ResponseResult(ResultEnum.PARAM_ERROR.getCode(),
				errmsg
		);
	}


}
