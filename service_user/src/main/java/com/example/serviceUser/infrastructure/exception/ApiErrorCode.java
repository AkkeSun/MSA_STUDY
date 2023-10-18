package com.example.serviceUser.infrastructure.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApiErrorCode {

    PASSWORD_NOT_MATCHING("400", "비밀번호와 비밀번호 확인이 올바르지 않습니다"),
    USERNAME_IS_NULL("400", "이름이 비어있습니다"),
    PASSWORD_IS_NULL("400", "비밀번호를 입력하지 않았습니다"),
    PASSWORD_CHECK_IS_NULL("400", "비밀번호 확인을 입력하지 않았습니다"),
    PHONE_NUMBER_IS_NULL("400", "전화번호가 비어있습니다"),
    ADDRESS_IS_NULL("400", "주소가 비어있습니다"),
    SNS_SYNC_IS_NULL("400", "snsSync 가 비어있습니다"),
    SNS_SECRET_IS_NULL("400", "snsSecret 이 비어있습니다"),
    ALREADY_SAVED_USER("400", "이미 등록된 사용자 입니다"),
    INVALID_PHONE_NUMBER("400", "전화번호가 올바르지 않습니다"),
    USER_INFO_NOT_FOUND ("404", "존재하지 않는 사용자 정보입니다"),
    ACCESS_DENIED("400", "유효하지 않은 토큰 입니다");

    private final String errorCode;
    private final String message;

    public String errorCode() {
        return this.errorCode;
    }

    public String message() {
        return this.message;
    }

}
