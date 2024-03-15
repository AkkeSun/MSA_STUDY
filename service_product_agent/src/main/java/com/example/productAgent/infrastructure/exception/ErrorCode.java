package com.example.productAgent.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_PRODUCT_ID(1001, "유효하지 않은 상품 아이디 입니다"),

    INVALID_MESSAGE_TYPE(1002, "유효하지 않은 kafka 메시지 입니다"),

    // ... other error codes
    ;


    private final int code;

    private final String message;
}
