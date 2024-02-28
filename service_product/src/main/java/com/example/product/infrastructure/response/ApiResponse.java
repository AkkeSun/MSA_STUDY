package com.example.product.infrastructure.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

    private int code;
    private String message;
    private T data;

    public ApiResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.message = message;
        this.data = data;
    }

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }

    public static <T> ApiResponse<T> of(HttpStatus httpStatus, String message, T data) {
        return new ApiResponse<>(httpStatus, message, data);
    }

    public static <T> ApiResponse<T> of(HttpStatus httpStatus, T data) {
        return of(httpStatus, httpStatus.name(), data);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return of(HttpStatus.OK, data);
    }
}
