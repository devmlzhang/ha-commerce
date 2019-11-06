package com.ha.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ha.file.mapper")
public class HaFileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaFileServiceApplication.class, args);
    }

}
