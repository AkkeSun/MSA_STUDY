package com.example.product.infrastructure.exception;

import com.example.product.application.port.in.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExceptionService {

    public ProductResponse getExceptionDTO(RuntimeException e) {
        if (e instanceof ApiException) {
            ApiException ex = (ApiException) e;
            log.error("[exception service] " + ex.getMessage());
            return ProductResponse.ofFail(new ExceptionResponse(ex));
        }
        log.error("[exception service] " + e.getMessage());
        return ProductResponse.ofFail(new ExceptionResponse(e.getMessage()));
    }
}
