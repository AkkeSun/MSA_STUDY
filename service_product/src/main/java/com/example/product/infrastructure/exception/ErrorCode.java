package com.example.product.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_CATEGORY(400, "유효하지 않은 카테고리 입니다"),

    INVALID_PRODUCT(400, "유효하지 않은 상품 정보 입니다"),

    ACCESS_DENIED(401, "접근 권한이 없습니다"),

    INVALID_UPDATE_ITEM(1001, "업데이트 항목이 없습니다"),
    // ... other error codes
    ;


    private final int code;

    private final String message;
}
