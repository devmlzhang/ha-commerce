package com.ha.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.ha.common.response.ResponseResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import javax.servlet.http.HttpServletResponse;
/**
 * <p>
 *    错误拦截处理
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/19
 */
@Component
@Slf4j
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        //需要在默认的 SendErrorFilter 之前
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().containsKey("throwable");
    }

    @Override
    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            HttpServletResponse response = context.getResponse();
            Object e = context.get("throwable");
            int statusCode = (int) context.get("responseStatusCode");

            if (e != null && e instanceof ZuulException) {

                // 删除该异常信息,不然在下一个过滤器中还会被执行处理
                context.remove("throwable");
                // 根据具体的业务逻辑来处理
                context.setResponseStatusCode(statusCode);
                if(statusCode==HttpStatus.TOO_MANY_REQUESTS.value()){
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(JSONObject.toJSONString(ResponseResult.tooManyRequests()));
                }
                 
            }
        } catch (Exception ex) {
            log.error("Exception filtering in custom error filter", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }
}