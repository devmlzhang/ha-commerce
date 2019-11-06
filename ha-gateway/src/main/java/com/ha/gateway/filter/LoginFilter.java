package com.ha.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.ha.common.enums.ResultEnum;
import com.ha.common.response.ResponseResult;
import com.ha.gateway.config.FilterConfig;
import com.ha.gateway.config.JwtConfig;
import com.ha.gateway.service.AuthService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *    登录拦截器
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/11
 */
@Component
@EnableConfigurationProperties({FilterConfig.class})
@Slf4j
public class LoginFilter extends ZuulFilter {

    @Autowired
    private FilterConfig filterConfig;
    @Autowired
    private AuthService authService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        //1.获取上下文
        RequestContext context = RequestContext.getCurrentContext();
        //2.获取request
        HttpServletRequest request = context.getRequest();
        //3.获取路径
        String requestUri = request.getRequestURI();
        log.info(requestUri);
        //4.判断白名单
        return !isAllowPath(requestUri);
    }

    private boolean isAllowPath(String requestUri) {
        //1.定义一个标记
        boolean flag = false;

        //2.遍历允许访问的路径
        List<String> paths = Arrays.asList(this.filterConfig.getAllowPaths().split(" "));
        for (String path : paths){
            if (requestUri.startsWith(path)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Object run() throws ZuulException {
        //过虑器的内容
        //过虑所有请求，判断头部信息是否有Authorization，如果没有则拒绝访问，否则转发到微服务。
        RequestContext requestContext = RequestContext.getCurrentContext();
        //得到request
        HttpServletRequest request = requestContext.getRequest();
        //得到response
        HttpServletResponse response = requestContext.getResponse();

        //从header中取jwt
        String jwtFromHeader = authService.getJwtFromHeader(request);
        if(StringUtils.isEmpty(jwtFromHeader)){
            //拒绝访问
            access_denied();
            return null;
        }
        //从redis取出jwt的过期时间
      /*  long expire = authService.getExpire(tokenFromCookie);
        if(expire<0){
            //拒绝访问
            access_denied();
            return null;
        }*/

        return null;
    }


    //拒绝访问
    private void access_denied(){
        RequestContext requestContext = RequestContext.getCurrentContext();
        //得到response
        HttpServletResponse response = requestContext.getResponse();
        //拒绝访问
        requestContext.setSendZuulResponse(false);
        //设置响应代码
        requestContext.setResponseStatusCode(200);
        //构建响应的信息
        ResponseResult responseResult = new ResponseResult(ResultEnum.UNAUTHENTICATED);
        //转成json
        String jsonString = JSON.toJSONString(responseResult);
        requestContext.setResponseBody(jsonString);
        //转成json，设置contentType
        response.setContentType("application/json;charset=utf-8");
    }

}
