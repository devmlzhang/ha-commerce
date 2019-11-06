package com.ha.search;

import com.ha.search.pojo.Goods;
import com.ha.search.repository.GoodsRepository;
import com.nepxion.aquarius.lock.LockExecutor;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HaSearchApplicationTests {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void contextLoads() {
        // 词条查询
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("subTitle", "短主题1");
        // 执行查询
        Iterable<Goods> items = this.goodsRepository.search(queryBuilder);
        items.forEach(System.out::println);
    }

    @Test
    public void addTest() {
        // 创建索引
        this.elasticsearchTemplate.createIndex(Goods.class);
        // 配置映射
        this.elasticsearchTemplate.putMapping(Goods.class);
        List<Goods> goodsList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Goods goods = new Goods();
            goods.setId(Long.valueOf(100+i));
            goods.setBrandId(Long.valueOf(200+i));
            goods.setSkus("测试"+i);
            goods.setSubTitle("短主题"+i);
            goodsList.add(goods);
        }
        this.goodsRepository.saveAll(goodsList);
    }

    @Test
    public void deleteTest() {
        elasticsearchTemplate.deleteIndex(Goods.class);
    }

    @Test
    public void testNativeQuery(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("subTitle", "短主题"));

        // 初始化分页参数
        int page = 0;
        int size = 3;
        // 设置分页参数
        queryBuilder.withPageable(PageRequest.of(page, size));

        // 执行搜索，获取结果
        Page<Goods> goods = goodsRepository.search(queryBuilder.build());
        // 打印总条数
        System.out.println("总条数："+goods.getTotalElements());
        // 打印总页数
        System.out.println("总页数："+goods.getTotalPages());
        // 每页大小
        System.out.println("每页大小："+goods.getSize());
        // 当前页
        System.out.println("当前页："+goods.getNumber());
        goods.forEach(v->{
            System.out.println(v.toString());
        });
    }


}
