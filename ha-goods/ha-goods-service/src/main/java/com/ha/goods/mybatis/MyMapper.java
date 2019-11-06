package com.ha.goods.mybatis;


import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.IdsMapper;

/**
 * <p>
 *     Mapper插件接口，如需其他接口参考官方文档自行添加
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
