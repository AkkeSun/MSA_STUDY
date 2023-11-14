package com.example.gateway.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GatewayErrorCode {

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN("401", "권한 정보가 없는 토큰입니다"),
    AUTH_TOKEN_NOT_FOUND("401", "토큰을 입력하지 않았습니다"),
    ;

    private final String errorCode;
    private final String message;
}
