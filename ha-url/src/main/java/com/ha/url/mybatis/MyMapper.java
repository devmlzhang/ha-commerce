package com.ha.url.mybatis;


import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.IdsMapper;

/**
 * <p>
 *     Mapper插件通用接口
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
public interface MyMapper<T>
        extends
        tk.mybatis.mapper.common.BaseMapper<T>,
        ConditionMapper<T>,
        IdsMapper<T>,
        InsertListMapper<T>,
        ExampleMapper<T> {
}
