package com.example.oauth2.infrastructure.utils;

import com.example.oauth2.domain.Token;

public interface JwtUtils {

    Token convertToken(String accessToken);
}
