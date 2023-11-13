package com.example.oauth2.infrastructure.util;

import com.example.oauth2.infrastructure.exception.ApiErrorCode;
import com.example.oauth2.infrastructure.exception.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.jwt.JwtHelper;

public class TokenUtils {

    public static Token convertToken(String accessToken) {
        try {
            return (new ObjectMapper()).readValue(JwtHelper.decode(
                accessToken.replace("Bearer ", "")).getClaims(), Token.class);
        } catch (Exception e) {
            throw new ApiException(ApiErrorCode.TOKEN_CONVERT_ERROR);
        }
    }
}
