package com.ha.search.vo;



import com.ha.common.pojo.PageResult;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *    搜索结果存储对象
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/16
 */
public class SearchResult<Goods> extends PageResult<Goods> {

    /**
     * 商品的集合
     */
    private  List<Goods> goods;

    /**
     * 规格参数的过滤条件
     */
    private List<Map<String,Object>> specs;

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public List<Map<String, Object>> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Map<String, Object>> specs) {
        this.specs = specs;
    }



    public SearchResult(Long total, Long totalPage, List<Goods> goods){
        super(total,totalPage,goods);
        this.goods = goods;
    }
}
