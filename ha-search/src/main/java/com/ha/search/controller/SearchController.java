package com.ha.search.controller;

import com.ha.common.pojo.PageResult;
import com.ha.search.bo.SearchRequest;
import com.ha.search.client.CategorClient;
import com.ha.search.pojo.Goods;
import com.ha.search.repository.GoodsRepository;
import com.ha.search.service.impl.SearchServiceImpl;
import com.ha.search.vo.SearchResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping
public class SearchController implements InitializingBean {

    @Autowired
    private SearchServiceImpl searchService;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private CategorClient categorClient;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @PostMapping("page")
    public ResponseEntity<PageResult<Goods>> search(@RequestBody SearchRequest searchRequest){
        SearchResult<Goods> result = this.searchService.search(searchRequest);
        if (result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return ResponseEntity.ok(result);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 创建索引
       // this.elasticsearchTemplate.createIndex(Goods.class);
        // 配置映射
       // this.elasticsearchTemplate.putMapping(Goods.class);
        List<Goods> goodsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Goods goods = new Goods();
            goods.setId(Long.valueOf(100+i));
            goods.setBrandId(Long.valueOf(200+i));
            goods.setSkus("测试"+i);
            goods.setSubTitle("短主题"+i);
            goodsList.add(goods);
        }
        //this.goodsRepository.saveAll(goodsList);

       /* //加载数据
        List<SpuBo> list = new ArrayList<>();
        int page = 1;
        int row = 100;
        int size;
        do {
            //分页查询数据
            PageResult<SpuBo> result = this.goodsClient.querySpuByPage(page, row, null, true, null, true);
            List<SpuBo> spus = result.getItems();
            size = spus.size();
            page ++;
            list.addAll(spus);
        }while (size == 100);

        //创建Goods集合
        List<Goods> goodsList = new ArrayList<>();
        //遍历spu
        for (SpuBo spu : list) {
            try {
                Goods goods = this.searchService.buildGoods(spu);
                goodsList.add(goods);
            } catch (IOException e) {
                System.out.println("查询失败：" + spu.getId());
            }
        }
        this.goodsRepository.saveAll(goodsList);*/

    }
}
