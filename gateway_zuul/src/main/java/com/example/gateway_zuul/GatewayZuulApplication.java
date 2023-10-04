package com.example.gateway_zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy // zuul proxy 선언
@SpringBootApplication
public class GatewayZuulApplication {

    public static void main(String[] args) {
        //SpringApplication.run(GatewayZuulApplication.class, args);
    }

}
