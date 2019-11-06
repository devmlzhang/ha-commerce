package com.ha.common.enums;

/**
 * <p>
 *     字典枚举
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
public enum DictEnum {


    /**
     * 类型
     */
    DICT_ENTERPRISE_CATEGORY(12,"enterprise_category","企业类别"),
    DICT_ENTERPRISE_NATURE(13,"enterprise_nature","企业性质")
    ;

    /**
     * 状态
     */
    private Integer code;

    /**
     * 值
     */
    private String value;

    /**
     * 名称
     */
    private String name;

    DictEnum(Integer code, String value, String name) {
        this.code = code;
        this.value = value;
        this.name = name ;
    }

    public Integer getCode() {
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

    public static DictEnum valueOfEnum(Integer code) {
        if(code != null ){
            DictEnum[] iss = values();
            for (DictEnum cs : iss) {
                if (cs.getCode().equals(code)) {
                    return cs;
                }
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }}
