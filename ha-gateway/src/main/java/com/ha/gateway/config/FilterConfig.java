package com.ha.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *    过滤白名单
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/11
 */
@ConfigurationProperties(prefix = "ha.filter")
@Configuration
@Data
public class FilterConfig {

    private String allowPaths;

}
