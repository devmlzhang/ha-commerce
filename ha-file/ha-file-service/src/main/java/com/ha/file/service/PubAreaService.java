package com.ha.file.service;


import com.ha.file.pojo.PubArea;
import com.ha.file.request.PubAddressReq;

import java.util.List;

/**
 * <p>
 *    区域信息
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
public interface PubAreaService {


	/**
	 * 查询省|市下面的子集(省 pid=0)
	 * @param pId
	 * @return
	 */
	List<PubArea> listByPId(String pId);

	/**
	 * 根据区域编码查询信息
	 * @param areaId
	 * @return
	 */
    PubArea getByAreadId(String areaId);

	/**
	 * 根据区域编码查询父级信息
	 * @param areaId
	 * @return
	 */
    PubArea getFatherByCode(String areaId);

	/**
	 * 获取详细地址
	 * @param req
	 * @return
	 */
	String getAddress(PubAddressReq req);
}
