package com.ha.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HaSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaSmsApplication.class, args);
    }

}
