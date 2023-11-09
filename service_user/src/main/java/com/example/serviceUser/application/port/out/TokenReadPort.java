package com.example.serviceUser.application.port.out;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="oauth2", url="${token.url}")
public interface TokenReadPort {

    @GetMapping("/userinfo")
    TokenInfo getTokenInfo(@RequestHeader("Authorization") String accessToken);
}
