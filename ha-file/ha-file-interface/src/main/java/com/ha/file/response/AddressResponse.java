package com.ha.file.response;

import lombok.Data;

import java.io.Serializable;
/**
 * <p>
 *  地址信息
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
@Data
public class AddressResponse implements Serializable {

	private String provinceId;

	private String cityId;

	private String districtId;

	private String street;

	private String address;
	
	private String provinceName;
    
    private String cityName;
    
    private String districtName;

	private static final long serialVersionUID = 1L;

}