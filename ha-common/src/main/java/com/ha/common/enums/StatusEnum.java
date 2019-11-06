package com.ha.common.enums;

/**
 * <p>
 *    数据状态
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
public enum StatusEnum {
    EFFECTIVE(1, "有效"),
    INVALID(0, "无效");

    private Integer code;
    private String value;

    StatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
