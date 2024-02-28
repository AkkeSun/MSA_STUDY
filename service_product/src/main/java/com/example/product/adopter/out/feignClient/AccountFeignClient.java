package com.example.product.adopter.out.feignClient;

import static com.example.product.infrastructure.utils.Constants.ACCOUNT_BREAKER_DEFAULT_VALUE;

import com.example.product.domain.Account;
import com.example.product.infrastructure.response.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.LinkedHashMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "oauth2", url = "${oauth2.url}")
public interface AccountFeignClient {

    @GetMapping("/oauth/account")
    @CircuitBreaker(name = "account", fallbackMethod = "fallback")
    ApiResponse<Account> getAccount(@RequestHeader("Authorization") String accessToken);

    default ApiResponse fallback(Exception e) {
        LinkedHashMap<String, String> defaultResponse = new LinkedHashMap<>();
        defaultResponse.put("userId", ACCOUNT_BREAKER_DEFAULT_VALUE);
        return ApiResponse.ok(defaultResponse);
    }
}
