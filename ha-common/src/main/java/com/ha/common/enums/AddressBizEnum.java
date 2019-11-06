package com.ha.common.enums;


/**
 * <p>
 *    地址枚举业务类型
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
public enum AddressBizEnum {
	
	/**
	 * 项目地址
	 */
	BIZENUM_1(1,"营地地址"),
	BIZENUM_2(2,"其他地址"),
	;
	
	/**
	 * 编码
	 */
	private int code;
	
	/**
	 * 名称
	 */
	private String value;
	
	AddressBizEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static AddressBizEnum valueOfEnum(int status) {
		AddressBizEnum[] iss = values();  
        for (AddressBizEnum cs : iss) {  
            if (cs.getCode() == status) {
                return cs;  
            }  
        }  
        return null;  
    }
	
}
