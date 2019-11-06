package com.ha.gateway.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * <p>
 *    jwt属性
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/11
 */
//@ConfigurationProperties(prefix = "ha.jwt")
//@Configuration
//@Data
//@Slf4j
public class JwtConfig {

    /**
     * JWT存储的请求头
     */
    private String tokenHeader;
    /**
     * JWT负载中拿到开头
     */
    private String tokenHead;
    /**
     * JWT加解密使用的密钥
     */
    private String secret;
    /**
     * 过期时间,单位分钟
     */
    private int expiration;




}
