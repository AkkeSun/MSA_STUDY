package com.example.service_monitoring.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
@Builder
public class ServiceError {

    @Id
    private String id;
    private String datetime;
    private String service;
    private String errorCode;
    private String message;
}
