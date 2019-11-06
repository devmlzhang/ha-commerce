package com.ha.file.service.impl;


import com.ha.file.mapper.PubAreaMapper;
import com.ha.file.pojo.PubArea;
import com.ha.file.request.PubAddressReq;
import com.ha.file.service.PubAreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *    区域信息实现类
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
@Service
public class PubAreaServiceImpl implements PubAreaService {

	@Autowired
    private PubAreaMapper pubAreaMapper;


	@Override
    public List<PubArea> listByPId(String pId) {
		if( StringUtils.isNotBlank(pId)){
            Example example= new Example(PubArea.class);
            example.createCriteria().andEqualTo("father",pId);
            return pubAreaMapper.selectByExample(example);
		}
		return new ArrayList<>();
	}

	@Override
	public PubArea getByAreadId(String areaId) {
		if( StringUtils.isNotBlank(areaId)) {
            Example example= new Example(PubArea.class);
            example.createCriteria().andEqualTo("area_id",areaId);
            return pubAreaMapper.selectOneByExample(example);
		}
		return null;
	}

	@Override
	public PubArea getFatherByCode(String areaId) {
		if( StringUtils.isNotBlank(areaId)) {

            Example example= new Example(PubArea.class);
            example.createCriteria().andEqualTo("area_id",areaId);
            PubArea area = pubAreaMapper.selectOneByExample(example);
			if( area != null ){
				example = new Example(PubArea.class);
				example.createCriteria().andEqualTo("area_id",area.getFather());
				return pubAreaMapper.selectOneByExample(example);
			}
		}
		return null;
	}

	@Override
	public String getAddress(PubAddressReq req) {
		if(req==null) {
            return "" ;
        }
		StringBuilder sb = new StringBuilder( ) ;
		if(StringUtils.isNotBlank(req.getProvinceId())){
			PubArea area = this.getByAreadId(req.getProvinceId());
			if(area!=null) {
                sb.append(area.getName()) ;
            }
		}
		if(StringUtils.isNotBlank(req.getCityId())){
			PubArea area = this.getByAreadId(req.getCityId());
			if(area!=null) {
                sb.append(area.getName()) ;
            }
		}
		if(StringUtils.isNotBlank(req.getDistrictId())){
			PubArea area = this.getByAreadId(req.getDistrictId());
			if(area!=null) {
                sb.append(area.getName()) ;
            }
		}
		if(StringUtils.isNotBlank(req.getStreet())){
			sb.append(req.getStreet()) ;
		}
		return sb.toString();
	}
}
