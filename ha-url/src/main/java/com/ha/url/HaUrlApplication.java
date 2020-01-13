package com.ha.url;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ha.url.mapper")
public class HaUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaUrlApplication.class, args);
    }

}
