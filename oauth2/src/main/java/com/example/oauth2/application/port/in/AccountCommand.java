package com.example.oauth2.application.port.in;

import static com.example.oauth2.infrastructure.exception.ApiErrorCode.ADDRESS_IS_NULL;
import static com.example.oauth2.infrastructure.exception.ApiErrorCode.INVALID_PHONE_NUMBER;
import static com.example.oauth2.infrastructure.exception.ApiErrorCode.INVALID_ROLE;
import static com.example.oauth2.infrastructure.exception.ApiErrorCode.LAST_LOGIN_TIME_IS_NULL;
import static com.example.oauth2.infrastructure.exception.ApiErrorCode.PASSWORD_CHECK_IS_NULL;
import static com.example.oauth2.infrastructure.exception.ApiErrorCode.PASSWORD_IS_NULL;
import static com.example.oauth2.infrastructure.exception.ApiErrorCode.PASSWORD_NOT_MATCHING;
import static com.example.oauth2.infrastructure.exception.ApiErrorCode.PHONE_NUMBER_IS_NULL;
import static com.example.oauth2.infrastructure.exception.ApiErrorCode.RULE_IS_NULL;
import static com.example.oauth2.infrastructure.exception.ApiErrorCode.SNS_SECRET_IS_NULL;
import static com.example.oauth2.infrastructure.exception.ApiErrorCode.SNS_SYNC_IS_NULL;
import static com.example.oauth2.infrastructure.exception.ApiErrorCode.USER_ID_IS_NULL;

import com.example.oauth2.adopter.out.Role;
import com.example.oauth2.infrastructure.exception.ApiException;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class AccountCommand {

    private String userId;
    private String password;
    private String passwordCheck;
    private String name;
    private String role;
    private String phoneNumber;
    private String address;
    private String snsSync;
    private String snsSecret;
    private LocalDateTime lastLoginTime;

    public void validation() {
        if (!StringUtils.hasText(userId)) {
            throw new ApiException(USER_ID_IS_NULL);
        }
        if (!StringUtils.hasText(password)) {
            throw new ApiException(PASSWORD_IS_NULL);
        }
        if (!StringUtils.hasText(passwordCheck)) {
            throw new ApiException(PASSWORD_CHECK_IS_NULL);
        }
        if (!StringUtils.hasText(phoneNumber)) {
            throw new ApiException(PHONE_NUMBER_IS_NULL);
        }
        if (!StringUtils.hasText(address)) {
            throw new ApiException(ADDRESS_IS_NULL);
        }
        if (!StringUtils.hasText(role)) {
            throw new ApiException(RULE_IS_NULL);
        }
        if (!password.equals(passwordCheck)) {
            throw new ApiException(PASSWORD_NOT_MATCHING);
        }
        if (!phoneNumber.matches("\\d{3}-\\d{3,4}-\\d{4}")) {
            throw new ApiException(INVALID_PHONE_NUMBER);
        }
        if (Role.getByValue(role) == null) {
            throw new ApiException(INVALID_ROLE);
        }
    }

    public void snsUserValidation() {
        if (!StringUtils.hasText(userId)) {
            throw new ApiException(USER_ID_IS_NULL);
        }
        if (!StringUtils.hasText(phoneNumber)) {
            throw new ApiException(PHONE_NUMBER_IS_NULL);
        }
        if (!StringUtils.hasText(snsSync)) {
            throw new ApiException(SNS_SYNC_IS_NULL);
        }
        if (!StringUtils.hasText(snsSecret)) {
            throw new ApiException(SNS_SECRET_IS_NULL);
        }
        if (!StringUtils.hasText(role)) {
            throw new ApiException(RULE_IS_NULL);
        }
        if (!phoneNumber.matches("\\d{3}-\\d{3,4}-\\d{4}")) {
            throw new ApiException(INVALID_PHONE_NUMBER);
        }
        if (Role.getByValue(role) == null) {
            throw new ApiException(INVALID_ROLE);
        }
    }

    public void updateValidation() {
        if (!StringUtils.hasText(userId)) {
            throw new ApiException(USER_ID_IS_NULL);
        }
        if (!StringUtils.hasText(phoneNumber)) {
            throw new ApiException(PHONE_NUMBER_IS_NULL);
        }
        if (!phoneNumber.matches("\\d{3}-\\d{3,4}-\\d{4}")) {
            throw new ApiException(INVALID_PHONE_NUMBER);
        }
        if (!StringUtils.hasText(role)) {
            throw new ApiException(RULE_IS_NULL);
        }
        if (StringUtils.hasText(snsSync)) {
            if (!StringUtils.hasText(snsSecret)) {
                throw new ApiException(SNS_SECRET_IS_NULL);
            }
        }
        if (StringUtils.hasText(password)) {
            if (!password.equals(passwordCheck)) {
                throw new ApiException(PASSWORD_NOT_MATCHING);
            }
        }
        if (Role.getByValue(role) == null) {
            throw new ApiException(INVALID_ROLE);
        }
    }

    public void updateLoginTimeValidation() {
        if (!StringUtils.hasText(userId)) {
            throw new ApiException(USER_ID_IS_NULL);
        }
        if (lastLoginTime == null) {
            throw new ApiException(LAST_LOGIN_TIME_IS_NULL);
        }
    }

}
