package com.example.oauth2.infrastructure.utils.impl;

import static com.example.oauth2.infrastructure.exception.ErrorCode.INVALID_TOKEN;

import com.example.oauth2.domain.Token;
import com.example.oauth2.infrastructure.exception.CustomException;
import com.example.oauth2.infrastructure.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;

@Component
public class JwtUtilsImpl implements JwtUtils {

    public Token convertToken(String accessToken) {
        try {
            return (new ObjectMapper()).readValue(JwtHelper.decode(
                accessToken.replace("Bearer ", "")).getClaims(), Token.class);
        } catch (Exception e) {
            throw new CustomException(INVALID_TOKEN);
        }
    }
}
