package com.ha.auth.service;

import com.alibaba.fastjson.JSON;
import com.ha.auth.client.AuthServiceClient;
import com.ha.auth.pojo.AuthToken;
import com.ha.common.enums.ResultEnum;
import com.ha.common.exception.ExceptionCast;
import com.ha.user.pojo.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class AuthService {

    @Value("${auth.tokenValiditySeconds}")
    int tokenValiditySeconds;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AuthServiceClient authServiceClient;

    @Autowired
    RestTemplate restTemplate;
    //用户认证申请令牌，将令牌存储到redis
    public AuthToken login(String username, String password, String clientId, String clientSecret) {

        //请求spring security申请令牌
        AuthToken authToken = this.applyToken(username, password, clientId, clientSecret);
       // authServiceClient.getToken("","password",username,password);
        if(authToken == null){
            ExceptionCast.cast(ResultEnum.AUTH_LOGIN_APPLYTOKEN_FAIL);
        }
        //用户身份令牌
        String accessToken = authToken.getAccessToken();
        //存储到redis中的内容
        String jsonString = JSON.toJSONString(authToken);
        //将令牌存储到redis
        boolean result = this.saveToken(accessToken, jsonString, tokenValiditySeconds);
        if (!result) {
            ExceptionCast.cast(ResultEnum.AUTH_LOGIN_TOKEN_SAVEFAIL);
        }
        return authToken;

    }
    //存储到令牌到redis

    /**
     *
     * @param accessToken 用户身份令牌
     * @param content  内容就是AuthToken对象的内容
     * @param ttl 过期时间
     * @return
     */
    private boolean saveToken(String accessToken,String content,long ttl){
        String key = "user_token:" + accessToken;
        stringRedisTemplate.boundValueOps(key).set(content,ttl, TimeUnit.SECONDS);
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire>0;
    }
    //删除token
    public boolean delToken(String accessToken){
        String key = "user_token:" + accessToken;
        stringRedisTemplate.delete(key);
        return true;
    }
    //从redis查询令牌
    public AuthToken getUserToken(String token){
        String key = "user_token:" + token;
        //从redis中取到令牌信息
        String value = stringRedisTemplate.opsForValue().get(key);
        //转成对象
        try {
            AuthToken authToken = JSON.parseObject(value, AuthToken.class);
            return authToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    //申请令牌
    private AuthToken applyToken(String username, String password, String clientId, String clientSecret){
        AuthToken authToken = new AuthToken();
        JWT jwt = authServiceClient.getToken(getHttpBasic(clientId, clientSecret), "password", username, password);
        if(jwt!=null){
            authToken.setAccessToken(jwt.getJti());//jti短令牌 用户身份令牌
            authToken.setRefreshToken(jwt.getRefresh_token());//刷新令牌
            authToken.setJwtToken(jwt.getAccess_token());//jwt令牌
        }
        return authToken;
    }

    //获取httpbasic的串
    private String getHttpBasic(String clientId,String clientSecret){
        String string = clientId+":"+clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic "+new String(encode);
    }
}
