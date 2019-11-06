package com.ha.file.service.impl;



import com.ha.common.enums.AddressBizEnum;
import com.ha.common.utils.IDUtil;
import com.ha.file.mapper.PubAddressMapper;
import com.ha.file.mapper.PubAreaMapper;
import com.ha.file.pojo.PubAddress;
import com.ha.file.pojo.PubArea;
import com.ha.file.request.PubAddressReq;
import com.ha.file.response.AddressResponse;
import com.ha.file.service.PubAreaService;
import com.ha.file.service.PubAddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * <p>
 *    公用地址信息实现类
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
@Service
public class PubAddressServiceImpl implements PubAddressService {

	@Autowired
	private PubAddressMapper addressMapper;
	@Autowired
	private PubAreaMapper areaMapper;
	
	@Autowired
	private PubAreaService areaService;
	
	@Override
	public String saveOrUpdateAddress(PubAddressReq model) {

		Example example = new Example(PubAddress.class);
		example.createCriteria().andEqualTo("biz_id",model.getBizId()).andEqualTo("biz_type",model.getBizType());
		List<PubAddress> list = addressMapper.selectByExample(example);

		PubAddress pubAddress = new PubAddress();
		if (list!=null && list.size()>0) {
			BeanUtils.copyProperties(model, pubAddress);
			PubAddress temp = list.get(0);
			pubAddress.setId(temp.getId());
			pubAddress.setAddress(this.getDetailAddress(model));

			this.dealAddress(pubAddress);
			addressMapper.updateByPrimaryKey(pubAddress);
		}else {
			BeanUtils.copyProperties(model, pubAddress);
			pubAddress.setId(IDUtil.getId());
			pubAddress.setAddress(this.getDetailAddress(model));

			this.dealAddress(pubAddress);
			addressMapper.insertSelective(pubAddress);
		}
		return pubAddress.getAddress() ;
	}
	
	
	
	@Override
	public AddressResponse queryByBizIdAndType(String bizId, Integer type) {
		AddressBizEnum bizEnum = AddressBizEnum.valueOfEnum(type);
		Example example = new Example(PubAddress.class);
		example.createCriteria().andEqualTo("biz_id",bizId).andEqualTo("biz_type",bizEnum.getValue());
		PubAddress pubAddress = addressMapper.selectOneByExample(example);

		if (pubAddress!=null) {
			AddressResponse address = new AddressResponse();
			BeanUtils.copyProperties(pubAddress, address);
			PubArea area = null;
			if (StringUtils.isNotBlank(pubAddress.getProvinceId())) {
				area = areaService.getByAreadId(pubAddress.getProvinceId());
				if (area != null) {
					address.setProvinceName(area.getName());
				}
			}
			if (StringUtils.isNotBlank(pubAddress.getCityId())) {
				area = areaService.getByAreadId(pubAddress.getCityId());
				if (area != null) {
					address.setCityName(area.getName());
				}
			}
			if (StringUtils.isNotBlank(pubAddress.getDistrictId())) {
				area = areaService.getByAreadId(pubAddress.getDistrictId());
				if (area != null) {
					address.setDistrictName(area.getName());
				}
			}
			return address ;
		}
		return null ;
	}
	
	/**
	 * 获取详细地址
	 * @param model
	 * @return
	 */
	private String getDetailAddress(PubAddressReq model) {
		StringBuffer address = new StringBuffer();
		PubArea area = null;
		if (StringUtils.isNotBlank(model.getProvinceId())) {
			area = areaMapper.selectByPrimaryKey(model.getProvinceId());
			if ( area != null ) {
				address.append(area.getName());
			}
		}
		if (StringUtils.isNotBlank(model.getCityId())) {
			area = areaMapper.selectByPrimaryKey(model.getCityId());
			if ( area != null ) {
				address.append(area.getName());
			}
		}
		if (StringUtils.isNotBlank(model.getDistrictId())) {
			area = areaMapper.selectByPrimaryKey(model.getDistrictId());
			if ( area != null ) {
				address.append(area.getName());
			}
		}
		if(StringUtils.isNotBlank(model.getStreet())){
			address.append(model.getStreet());
		}
		return address.toString();
	}

	/**
	 * 处理地址信息
	 * @return
	 */
	private void dealAddress(PubAddress address){
		if( address != null ){
			if("undefined".equals(address.getProvinceId())){
				address.setProvinceId("");
			}
			if("undefined".equals(address.getCityId())){
				address.setCityId("");
			}
			if("undefined".equals(address.getDistrictId())){
				address.setDistrictId("");
			}
		}
	}
}
