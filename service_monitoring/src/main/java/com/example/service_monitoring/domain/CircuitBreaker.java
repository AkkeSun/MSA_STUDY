package com.example.service_monitoring.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
@AllArgsConstructor
public class CircuitBreaker {

    @Id
    private String id;
    private String service;
    private String message;
    private String datetime;

    public void setDatetime() {
        datetime = LocalDateTime.now().toString();
    }
}
