package com.ha.search.service;


import com.ha.search.bo.SearchRequest;
import com.ha.search.pojo.Goods;
import com.ha.search.vo.SearchResult;

import java.io.IOException;

/**
 * <p>
 *    搜索功能
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/16
 */
public interface SearchService {


    /**
     * 商品搜索
     * @param searchRequest
     * @return
     */
    SearchResult<Goods> search(SearchRequest searchRequest);


    /**
     * 根据goods的id创建相应的索引
     * @param id
     * @throws IOException
     */
    void createIndex(Long id) throws IOException;

    /**
     * 根据goods的id删除相应的索引
     * @param id
     */
    void deleteIndex(Long id);
}
