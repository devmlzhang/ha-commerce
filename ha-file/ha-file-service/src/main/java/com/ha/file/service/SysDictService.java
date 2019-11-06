package com.ha.file.service;

import com.ha.file.pojo.SysDict;

import java.util.List;

/**
 * <p>
 *    系统字段服务
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
public interface SysDictService {

    /**
     * 根据组名查询字典值
     * @param groupName 组名
     * @return
     */
    List<SysDict> findByGroup(String groupName) ;

    /**
     * 根据组名与字典值获取字典对象
     * @param groupName 组名
     * @param value 字典值
     * @return
     */
    SysDict getByGroupAndValue(String groupName, String value) ;

    /**
     * 根据组名和字典值获取字典属性名
     * @param groupName
     * @param value
     * @return
     */
    String getNameByGroupAndValue(String groupName, String value) ;

    /**
     * 根据组名与字典名称获取字典对象
     * @param groupName 组名
     * @param name 字典名称
     * @return
     */
    SysDict getByGroupAndName(String groupName, String name) ;

    /**
     * 批量插入字典值
     * @param group
     * @param groupName
     * @param dictNames
     * @param spile
     * @param pid
     */
    List<SysDict> batchInsertDict(String group, String groupName, String dictNames, String spile, String pid);

    /**
     * 根据父id获取字典值
     * @param pid
     * @return
     */
    List<SysDict> findByPid(String pid);

    /**
     * 根据父字典值
     * @param pValue
     * @param group
     * @return
     */
    List<SysDict> findByPValue(String pValue, String group);
}
