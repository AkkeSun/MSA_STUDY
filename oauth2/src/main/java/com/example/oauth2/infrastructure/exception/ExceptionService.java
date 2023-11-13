package com.example.oauth2.infrastructure.exception;

import com.example.oauth2.application.port.in.AccountResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExceptionService {

    public AccountResponse getExceptionDTO(RuntimeException e) {
        if (e instanceof ApiException) {
            ApiException ex = (ApiException) e;
            log.error("[exception service] " + ex.getMessage());
            return AccountResponse.ofFail(new ExceptionResponse(ex));
        }
        log.error("[exception service] " + e.getMessage());
        return AccountResponse.ofFail(new ExceptionResponse(e.getMessage()));
    }
}
