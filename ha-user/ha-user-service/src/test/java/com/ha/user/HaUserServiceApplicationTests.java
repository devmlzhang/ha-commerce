package com.ha.user;

import com.alibaba.fastjson.JSONObject;
import com.ha.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HaUserServiceApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMsg(){
        //生成六位数字随机数
        String checkCode = RandomStringUtils.randomNumeric(6);
        //向缓存中存入一份
//        redisTemplate.opsForValue().set("checkCode"+"13037808537",checkCode,6, TimeUnit.HOURS);
        Map<String,String> map = new HashMap<>();
        map.put("phone","13037808537");
        map.put("code",checkCode);
        //给用户发一份
       // rabbitTemplate.convertAndSend("ha.sms.exchange","ha.test",map);
//        this.amqpTemplate.convertAndSend("ha.sms.exchange","ha.verify.code",map);
        //为了测试方便，在控制台显示一份
        System.out.println("验证码为："+checkCode);
    }

    @Test
    public void send() {
        //this.amqpTemplate.convertAndSend("ha.sms.exchange","ha.creat", "123344");
        int i;
        for (i=0;i<=20;i++){
            amqpTemplate.convertAndSend("","ha.test","测试"+i);
            System.out.println(i);
        }
    }
}
