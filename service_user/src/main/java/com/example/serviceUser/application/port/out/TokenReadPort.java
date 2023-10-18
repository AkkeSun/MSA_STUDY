package com.example.serviceUser.application.port.out;

import com.example.serviceUser.adopter.out.TokenInfo;

public interface TokenReadPort {
    TokenInfo getTokenInfo(String accessToken);
}
