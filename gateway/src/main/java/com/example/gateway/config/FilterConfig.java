package com.example.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/*
    사용하는 경우 Bean 을 생성하고 application.yml 설정정보를 삭제해준다
 */
//@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(r -> r.path("/user/**")
                .filters(f -> f.addRequestHeader("reqKey", "reqVal")
                    .addResponseHeader("resKey", "resVal"))
                .uri("http://localhost:8081"))
            .route(r -> r.path("/catalog/**")
                .filters(f -> f.addRequestHeader("reqKey", "reqVal")
                    .addResponseHeader("resKey", "resVal"))
                .uri("http://localhost:8082"))
            .build();
    }
}