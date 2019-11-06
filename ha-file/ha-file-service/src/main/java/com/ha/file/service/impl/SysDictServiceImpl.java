package com.ha.file.service.impl;


import com.ha.common.utils.IDUtil;
import com.ha.file.mapper.SysDictMapper;
import com.ha.file.pojo.SysDict;
import com.ha.file.service.SysDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *    字典服务实现类
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
@Service
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public List<SysDict> findByGroup(String groupName) {
        //传入参数为空，返回空列表
        if(StringUtils.isBlank(groupName)){
            return Collections.EMPTY_LIST ;
        }
        Example example = new Example(SysDict.class);
        example.createCriteria().andEqualTo("groups",groupName);
        example.setOrderByClause("sort ASC");
        List<SysDict> dicts = sysDictMapper.selectByExample(example);

        return dicts;
    }

    @Override
    public SysDict getByGroupAndValue(String groupName, String value) {
        //传入参数值为空
        if(StringUtils.isBlank(groupName)|| StringUtils.isBlank(value)){
            return null ;
        }
        List<SysDict> list = this.findByGroup(groupName);

        if(list!=null && list.size()>0){
            for (SysDict dict :list) {
                if(StringUtils.equals(dict.getValue(),value)){
                    return dict ;
                }
            }
        }

        return null ;
    }

    @Override
    public String getNameByGroupAndValue(String groupName, String value) {
        SysDict dict = this.getByGroupAndValue(groupName, value);
        if(dict==null) {
            return "" ;
        }
        return dict.getName();
    }

    @Override
    public SysDict getByGroupAndName(String groupName, String name) {
        //传入参数值为空
        if(StringUtils.isBlank(groupName) && StringUtils.isBlank(name)){
            return null ;
        }
        List<SysDict> list = this.findByGroup(groupName);

        if(list!=null && list.size()>0){
            for (SysDict dict :list) {
                if(StringUtils.equals(dict.getName(),name)) {
                    return dict ;
                }
            }
        }


        return null ;
    }

    @Override
    public List<SysDict> batchInsertDict(String group, String groupName, String dictNames, String spile, String pid) {
        if(StringUtils.isBlank(group) && StringUtils.isBlank(dictNames) && StringUtils.isBlank(spile)) {
            return null;
        }
        String[] split = dictNames.split(spile);
        if(StringUtils.isNotEmpty(spile)){
            List<SysDict> list = this.findByGroup(group) ;
            int i = list.size()+1 ;
            list = new ArrayList<>() ;

            SysDict dict = null ;
            for (String dictName :split) {
                dict = this.getByGroupAndName(group, dictName);
                if((dict!=null)){
                    continue;//存在不插入
                }
                dict = new SysDict() ;
                String key = group + "_" + i;
                dict.setId(IDUtil.getId());
                dict.setGroups(group);
                dict.setDescription(groupName);
                dict.setName(dictName);
                dict.setPId(pid);
                dict.setSort(i);
                dict.setCode(key);
                dict.setValue(i+"");
                i++ ;
                list.add(dict) ;
            }
            if(list!=null && list.size()>0) {
                sysDictMapper.insertList(list);
            }
            return list;
        }
        return null;
    }

    @Override
    public List<SysDict> findByPid(String pid) {
        //传入参数为空，返回空列表
        if(StringUtils.isBlank(pid)){
            return Collections.EMPTY_LIST ;
        }
        Example example = new Example(SysDict.class);
        example.createCriteria().andEqualTo("p_id",pid);
        example.setOrderByClause("sort ASC");
        List<SysDict> dicts = sysDictMapper.selectByExample(example);
        return dicts;
    }

    @Override
    public List<SysDict> findByPValue(String pValue, String group) {
        SysDict dict = this.getByGroupAndValue(group, pValue);
        if(dict!=null) {
            return this.findByPid(dict.getId()) ;
        }
        return Collections.emptyList();
    }
}
