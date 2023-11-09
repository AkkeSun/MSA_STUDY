package com.example.serviceUser.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExceptionService {
    public ExceptionResponse getExceptionDTO (RuntimeException e) {
        if(e instanceof ApiException) {
            ApiException ex = (ApiException) e;
            log.error("[exception service] " + ex.getMessage());
            return new ExceptionResponse(ex);
        }
        log.error("[exception service] " + e.getMessage());
        return new ExceptionResponse(e.getMessage());
    }
}
