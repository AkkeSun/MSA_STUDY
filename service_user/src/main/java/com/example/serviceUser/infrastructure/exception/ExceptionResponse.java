package com.example.serviceUser.infrastructure.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;

@Getter
public class ExceptionResponse {
    private String result;
    private String date;
    private String errorCode;
    private String message;

    public ExceptionResponse(ApiException e) {
        this.result = "N";
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.errorCode = e.getApiErrorCode().errorCode();
        this.message = e.getApiErrorCode().message();
    }

    public ExceptionResponse(String message) {
        this.result = "N";
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.errorCode = "500";
        this.message = message;
    }
}
