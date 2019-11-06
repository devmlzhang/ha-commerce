package com.ha.file.service;


import com.ha.common.enums.AddressBizEnum;
import com.ha.file.request.PubAddressReq;
import com.ha.file.response.AddressResponse;
import io.swagger.models.auth.In;

/**
 * <p>
 *    公用地址信息
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
public interface PubAddressService {

	/**
	 * 添加或者更新地址信息
	 * @param model
	 * @return
	 */
	String saveOrUpdateAddress(PubAddressReq model);

	/**
	 * 根据业务ID和业务类型进行查询
	 * @param bizId
	 * @param type
	 * @return
	 */
	AddressResponse queryByBizIdAndType(String bizId, Integer type);
	
	
}
