package com.example.product.application.port.out;

import static com.example.product.infrastructure.utils.Constants.ACCOUNT_BREAKER_DEFAULT_VALUE;

import com.example.product.application.port.in.ProductResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.LinkedHashMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "oauth2", url = "${oauth2.url}")
public interface AccountReadPort {

    @GetMapping("/oauth/account")
    @CircuitBreaker(name = "account", fallbackMethod = "fallback")
    ProductResponse getAccount(@RequestHeader("Authorization") String accessToken);

    default ProductResponse fallback(Exception e) {
        LinkedHashMap<String, String> defaultResponse = new LinkedHashMap<>();
        defaultResponse.put("userId", ACCOUNT_BREAKER_DEFAULT_VALUE);
        return ProductResponse.ofFail(defaultResponse);
    }
}
