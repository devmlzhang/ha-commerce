package com.ha.pay.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ha.pay.interceptor.AliPayInterceptor;
import com.ha.pay.interceptor.CharacterEncodInterceptor;
import com.ha.pay.interceptor.WxPayInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 *    WebMvc
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/5
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AliPayInterceptor()).addPathPatterns("/alipay/**");
		registry.addInterceptor(new WxPayInterceptor()).addPathPatterns("/wxpay/**", "/wxsubpay/**");
		registry.addInterceptor(new CharacterEncodInterceptor()).addPathPatterns("/unionpay/**");
		super.addInterceptors(registry);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 将所有/static/** 访问都映射到classpath:/static/ 目录下
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);

		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(

				SerializerFeature.WriteMapNullValue, // 保留map空的字段

				SerializerFeature.WriteNullStringAsEmpty, // 将String类型的null转成""

				SerializerFeature.WriteNullNumberAsZero, // 将Number类型的null转成0

				SerializerFeature.WriteNullListAsEmpty, // 将List类型的null转成[]

				SerializerFeature.WriteNullBooleanAsFalse, // 将Boolean类型的null转成false

				SerializerFeature.DisableCircularReferenceDetect);// 避免循环引用

		converter.setFastJsonConfig(config);
		converter.setDefaultCharset(StandardCharsets.UTF_8);
		List<MediaType> mediaTypeList = new ArrayList<MediaType>();
		// 解决中文乱码问题，相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
		mediaTypeList.add(MediaType.APPLICATION_JSON);
		converter.setSupportedMediaTypes(mediaTypeList);
		converters.add(converter);

		converters.add(responseBodyConverter());
	}

	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		return converter;
	}
}
