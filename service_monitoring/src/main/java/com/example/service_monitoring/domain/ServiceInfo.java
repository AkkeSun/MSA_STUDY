package com.example.service_monitoring.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

@Getter
@Document
@AllArgsConstructor
public class ServiceInfo {

    @Id
    private String id;
    private String name;
    private String host;
    private String testApi;
    private String datetime;

    public String validation() {
        if (!StringUtils.hasText(name)) {
            return "name 을 입력하지 않았습니다";
        }
        if (!StringUtils.hasText(host)) {
            return "host 를 않았습니다";
        }
        if (!StringUtils.hasText(testApi)) {
            return "testApi 를 않았습니다";
        }
        datetime = LocalDateTime.now().toString();
        return "";
    }

}
