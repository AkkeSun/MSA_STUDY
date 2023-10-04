package com.example.gateway_zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ZuulLoggingFilter extends ZuulFilter {

    // filter 실행 로직
    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        log.info("request - " + request.getRequestURI());
        return null;
    }

    // pre or post
    @Override
    public String filterType() {
        return "pre";
    }

    // filter 적용 순서
    @Override
    public int filterOrder() {
        return 1;
    }

    // filter 사용 유무
    @Override
    public boolean shouldFilter() {
        return true;
    }
}
