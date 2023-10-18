package com.example.serviceUser.adopter.out;

import com.example.serviceUser.application.port.out.TokenReadPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class TokenAdapter implements TokenReadPort {

    @Value("${token.url}")
    private String tokenUrl;

    private final ObjectMapper objectMapper;

    @Override
    public TokenInfo getTokenInfo(String accessToken) {
        String response = WebClient.builder().baseUrl(tokenUrl)
            .defaultHeader("Authorization", "Bearer " + accessToken)
            .build()
            .get()
            .uri("/userinfo")
            .retrieve()
            .bodyToMono(String.class)
            .block();
        try {
            return objectMapper.readValue(response, TokenInfo.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
