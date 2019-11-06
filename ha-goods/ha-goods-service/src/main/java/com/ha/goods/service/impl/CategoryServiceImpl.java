package com.ha.goods.service.impl;



import com.ha.goods.mapper.TestCategoryMapper;
import com.ha.goods.pojo.Category;
import com.ha.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private TestCategoryMapper categoryMapper;


    /**
     * 根据父节点id查询分类
     * @param pid
     * @return
     */
    @Override
    public List<Category> queryCategoryByPid(Long pid)  {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("parentId",pid);
        List<Category> list = this.categoryMapper.selectByExample(example);
        /*if (CollectionUtils.isEmpty(list)){
            throw new Exception(ResultEnum.PARAM_ERROR);
        }*/
        return list;
    }


}
