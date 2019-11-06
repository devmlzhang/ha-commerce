package com.ha.search.repository;

import com.ha.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <p>
 *    文档操作
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/16
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
