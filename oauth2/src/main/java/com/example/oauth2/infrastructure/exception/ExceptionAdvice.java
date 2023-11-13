package com.example.oauth2.infrastructure.exception;

import com.example.oauth2.application.port.in.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionAdvice {

    private final ExceptionService exceptionService;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AccountResponse> exceptionHandler(RuntimeException e) {
        return ResponseEntity.ok(exceptionService.getExceptionDTO(e));
    }
}
