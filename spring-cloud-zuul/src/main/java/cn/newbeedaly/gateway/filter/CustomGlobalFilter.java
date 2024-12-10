package cn.newbeedaly.gateway.filter;


import cn.newbeedaly.gateway.adaptor.user.UserFeignClient;
import cn.newbeedaly.gateway.adaptor.user.vo.UserValidTokenResVO;
import com.google.common.net.HttpHeaders;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Component
public class CustomGlobalFilter extends ZuulFilter {

    @Value("#{'/user/login,/user/getServerName,/actuator/**'.split(',')}")
    private List<String> ignoreUrls;

    @Autowired
    private UserFeignClient userFeignClient;


    @Override
    public String filterType() {
        // 路由执行位置：指定路由之前执行
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 路由执行顺序：系统最小值为-3，设置-5说明在所有的系统执行前执行
        return -5;
    }

    // 对请求进行过滤的核心逻辑
    @Override
    public boolean shouldFilter() {
        log.info("gateway filter...");
        RequestContext currentContext = RequestContext.getCurrentContext();
        if (ignoreUrls != null && ignoreUrls.contains(currentContext.getRequest().getRequestURI())) {
            return Boolean.TRUE;
        }
        String token = currentContext.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(token)) {
            onError("尚未登录");
            return Boolean.FALSE;
        }
        // WebFlux异步调用，同步会报错
        Future<UserValidTokenResVO> future = CompletableFuture.supplyAsync(() -> userFeignClient.validToken(token));
        try {
            UserValidTokenResVO validTokenResVO = future.get();
            if (Objects.nonNull(validTokenResVO)) {
                String userId = currentContext.getRequest().getHeader("userId");
                return StringUtils.equals(userId, String.valueOf(validTokenResVO.getId()));
            }
        } catch (InterruptedException | ExecutionException e) {
            onError("valid user token error");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    //校验通过的执行逻辑
    @Override
    public Object run() {
        log.info("校验通过");
        return null;
    }

    private void onError(String msg) {
        log.error(msg);
        RequestContext.getCurrentContext().setSendZuulResponse(false);
        RequestContext.getCurrentContext().setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
    }

}
