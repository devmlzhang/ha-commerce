package com.ha.search.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ha.common.utils.NumberUtils;
import com.ha.goods.pojo.Category;
import com.ha.search.bo.SearchRequest;
import com.ha.search.client.CategorClient;
import com.ha.search.pojo.Goods;
import com.ha.search.repository.GoodsRepository;
import com.ha.search.service.SearchService;
import com.ha.search.vo.SearchResult;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.InternalHistogram;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.stats.InternalStats;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *    搜索功能
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/16
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private CategorClient categoryClient;


    @Autowired
    private GoodsRepository goodsRepository;



    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 搜索
     * @param searchRequest
     * @return
     */
    @Override
    public SearchResult<Goods> search(SearchRequest searchRequest) {
        String key = searchRequest.getKey();

        /**
         * 判断是否有搜索条件，如果没有，直接返回null。不允许搜索全部商品
         */
        if (StringUtils.isBlank(key)){
            return null;
        }
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("subTitle", key));

        // 初始化分页参数
        int page = searchRequest.getPage();
        int size = searchRequest.getPageSize();
        // 设置分页参数
        queryBuilder.withPageable(PageRequest.of(page, size));

        // 执行搜索，获取结果
        Page<Goods> goods = goodsRepository.search(queryBuilder.build());
        return new SearchResult<>(goods.getTotalElements(), (long)goods.getTotalPages(),goods.getContent());
    }

    /**
     * 创建索引
     * @param id
     */
    @Override
    public void createIndex(Long id) throws IOException {
        //构建商品
        Goods goods =new Goods();

        //保存数据到索引库中
        this.goodsRepository.save(goods);
    }

    /**
     * 删除索引
     * @param id
     */
    @Override
    public void deleteIndex(Long id) {

        this.goodsRepository.deleteById(id);
    }
}
