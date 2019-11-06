package com.ha.goods;

import com.ha.common.interceptor.FeignClientInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;
/**
 * <p>
 *    
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/21 
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.ha.goods.mapper")
public class HaGoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaGoodsServiceApplication.class, args);
    }

    /**
     * 服务与服务之间调用拦截处理
     * @return
     */
    @Bean
    public FeignClientInterceptor getFeignClientInterceptor(){
        return new FeignClientInterceptor();
    }
}
