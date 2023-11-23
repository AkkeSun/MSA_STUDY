package com.example.product.infrastructure.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;

@Getter
public class ExceptionResponse {

    private String datetime;
    private String errorCode;
    private String message;

    public ExceptionResponse(ApiException e) {
        this.datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.errorCode = e.getApiErrorCode().errorCode();
        this.message = e.getApiErrorCode().message();
    }

    public ExceptionResponse(String message) {
        this.datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.errorCode = "500";
        this.message = message;
    }
}
