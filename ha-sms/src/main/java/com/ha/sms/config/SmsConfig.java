package com.ha.sms.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *    短信服务实体类
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/16
 */
@ConfigurationProperties(prefix = "hasms.sms")
@Configuration
@Data
public class SmsConfig {

    private String accessKeyId;

    private String accessKeySecret;

    private String signName;

    private String verifyCodeTemplate;


}
