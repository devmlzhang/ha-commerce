package com.ha.goods.mapper;

import com.ha.goods.pojo.Category;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;


@org.apache.ibatis.annotations.Mapper
public interface TestCategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category,Long> {


    /**
     * 根据id查名字
     * @param id
     * @return
     */
    @Select("SELECT name FROM tb_category WHERE id = #{id}")
    String queryNameById(Long id);

    /**
     * 测试
     * @param id
     * @return
     */
    Category getOneById(Long id);


}
