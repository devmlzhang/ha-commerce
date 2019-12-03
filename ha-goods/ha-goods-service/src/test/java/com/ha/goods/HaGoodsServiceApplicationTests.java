package com.ha.goods;

import com.ha.common.utils.IDUtil;
import com.ha.goods.mapper.CmsSiteRepository;
import com.ha.goods.pojo.CmsSite;
import com.ha.goods.service.GoodsLockService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HaGoodsServiceApplicationTests {
    @Autowired
    GoodsLockService goodsLockService;
    @Autowired
    CmsSiteRepository cmsSiteRepository;
    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    @Test
    public void addCmsSite() {
        CmsSite cmsSite = new CmsSite();
        cmsSite.setSiteId(IDUtil.nextId()+"");
        cmsSite.setSiteName("测试站点");
        cmsSite.setSiteCreateTime(new Date());
        cmsSite.setSitePort("3309");
        CmsSite insert = cmsSiteRepository.insert(cmsSite);
        System.out.println(insert.toString());
    }

    @Test
    public void getCmsSite() {
        Optional<CmsSite> optional = cmsSiteRepository.findById("7886142069185232896");
        if(optional.isPresent()){
            System.out.println(optional.get());
        }
    }

    //存文件
    @Test
    public void testStore() throws FileNotFoundException {
        DBObject metaData = new BasicDBObject();
        //定义file
        String logoUrl = "/Users/weirdo/Desktop/公章.png";
        File file =new File(logoUrl);
        //定义fileInputStream
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectId objectId = gridFsTemplate.store(fileInputStream, "logoUrl",metaData);
        System.out.println(objectId);
    }

    //取文件
    @Test
    public void queryFile() throws IOException {
        //根据文件id查询文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is("5b9cb02435794805b43b2b04")));

        //打开一个下载流对象
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //创建GridFsResource对象，获取流
        GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
        //从流中取数据
        String content = IOUtils.toString(gridFsResource.getInputStream(), "utf-8");
        System.out.println(content);

    }

}
