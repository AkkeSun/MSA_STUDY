package com.example.oauth2.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_ROLE("1001", "유효하지 않은 권한 입니다"),

    DIFFERENT_PASSWORD_AND_CHECK("1002", "비밀번호와 비밀번호 확인이 다릅니다"),


    ACCOUNT_NOT_FOUND("2001", "사용자 정보를 찾을 수 없습니다"),

    INVALID_TOKEN("3001", "유효하지 않는 토큰 입니다"),
    ;


    private final String code;

    private final String message;
}
