package com.ha.gateway;

import com.ha.gateway.filter.LoginFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class HaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaGatewayApplication.class, args);
    }

    @Bean
    public LoginFilter loginFilter() {
        return new LoginFilter();
    }



}
