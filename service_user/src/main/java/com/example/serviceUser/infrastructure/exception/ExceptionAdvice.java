package com.example.serviceUser.infrastructure.exception;

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
    public ResponseEntity<ExceptionResponse> exceptionHandler(RuntimeException e) {
        return ResponseEntity.ok(exceptionService.getExceptionDTO(e));
    }
}
