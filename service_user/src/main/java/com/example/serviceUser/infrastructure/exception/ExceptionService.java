package com.example.serviceUser.infrastructure.exception;

import org.springframework.stereotype.Service;

@Service
public class ExceptionService {
    public ExceptionResponse getExceptionDTO (RuntimeException e) {
        if(e instanceof ApiException) {
            return new ExceptionResponse((ApiException) e);
        }
        return new ExceptionResponse(e.getMessage());
    }
}
