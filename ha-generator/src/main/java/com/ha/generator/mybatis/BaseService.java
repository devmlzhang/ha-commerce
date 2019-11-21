package com.ha.generator.mybatis;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * <p>
 *    层 基础接口，其他Service 接口 请继承该接口
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
public interface BaseService<T> {
    /**
     * 保存
     * @param model
     */
    void save(T model);

    /**
     * 批量保存
     * @param models
     */
    void save(List<T> models);

    /**
     * 通过主鍵刪除
     * @param id
     */
    void deleteById(Long id);

    /**
     * 批量刪除 eg：ids -> “1,2,3,4”
     * @param ids
     */
    void deleteByIds(String ids);

    /**
     * 更新
     * @param model
     */
    void update(T model);

    /**
     * 通过ID查找
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
     * @param fieldName
     * @param value
     * @return
     * @throws TooManyResultsException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    T findBy(String fieldName, Object value) throws TooManyResultsException, NoSuchFieldException, IllegalAccessException, InstantiationException;

    /**
     * 通过多个ID查找//eg：ids -> “1,2,3,4”
     * @param ids
     * @return
     */
    List<T> findByIds(String ids);

    /**
     * 根据条件查找
     * @param condition
     * @return
     */
    List<T> findByCondition(Condition condition);

    /**
     * 获取所有
     * @return
     */
    List<T> findAll();

    /**
     * 根据Example查找
     * @param object
     * @return
     */
    List<T> selectByExample(Object object);

}
