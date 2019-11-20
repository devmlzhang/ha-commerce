package com.ha.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.ha.flow.mapper")
public class HaFlowServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaFlowServiceApplication.class, args);
    }

}
