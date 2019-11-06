package com.ha.gateway.config;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.DefaultRateLimiterErrorHandler;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.RateLimiterErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
@Slf4j
public class ZuulRatelimitConfiguration {

//    @Bean
    public RateLimiterErrorHandler rateLimitErrorHandler() {
        return new DefaultRateLimiterErrorHandler() {
            @Override
            public void handleSaveError(String key, Exception e) {
                // custom code
                log.error("handleSaveError:"+key+"===="+e);
            }

            @Override
            public void handleFetchError(String key, Exception e) {
                // custom code
                log.error("handleFetchError:"+key+"===="+e);
            }

            @Override
            public void handleError(String msg, Exception e) {
                // custom code
                log.error("handleError:"+msg+"===="+e);
            }
        };
    }
}