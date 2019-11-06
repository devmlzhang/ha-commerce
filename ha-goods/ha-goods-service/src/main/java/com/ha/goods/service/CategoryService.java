package com.ha.goods.service;


import com.ha.goods.pojo.Category;

import java.util.List;

/**
 * <p>
 *    测试接口
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/3
 */
public interface CategoryService {

    /**
     * 根据id查询分类
     * @param pid
     * @return
     */
    List<Category> queryCategoryByPid(Long pid) ;

}
