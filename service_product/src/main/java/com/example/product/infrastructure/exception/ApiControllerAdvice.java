package com.example.product.infrastructure.exception;

import com.example.product.infrastructure.response.ApiResponse;
import com.example.product.infrastructure.response.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ApiResponse<Object> bindException(BindException e) {
        return ApiResponse.of(
            HttpStatus.BAD_REQUEST,
            new ErrorDTO().of(e)
        );
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomException.class)
    public ApiResponse<Object> handleCustomBusinessException(CustomException ex) {
        log.info("CustomException : " + ex.getErrorCode().getMessage());
        return ApiResponse.of(
            HttpStatus.INTERNAL_SERVER_ERROR,
            new ErrorDTO().of(ex)
        );
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse<Object> handleInternalError(Exception e) throws Exception {
        log.error(e.getMessage());
        return ApiResponse.of(
            HttpStatus.INTERNAL_SERVER_ERROR,
            new ErrorDTO().of(e)
        );
    }
}
