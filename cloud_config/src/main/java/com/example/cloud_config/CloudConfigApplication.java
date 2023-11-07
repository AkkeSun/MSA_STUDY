package com.example.cloud_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/*
    설정파일 규칙 : {APP Name}-{profile}
    check endpoint
       - 저장된 파일 보기 : http://localhost:8888/common-real.yml
       - 엔드포인트로 보기 : http://localhost:8888/common/real
 */
@SpringBootApplication
@EnableConfigServer // 설정 서버 등록
public class CloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication.class, args);
    }

}
