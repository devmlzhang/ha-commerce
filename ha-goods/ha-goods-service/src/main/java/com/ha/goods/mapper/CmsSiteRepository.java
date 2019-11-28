package com.ha.goods.mapper;


import com.ha.goods.pojo.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {
}
