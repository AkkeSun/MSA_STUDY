package com.example.product.application.port.out;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CircuitBreakerMsg {

    private String service;
    private Map<String, Object> message;
    private String datetime;

    public CircuitBreakerMsg(Integer productId, String accessToken) {
        service = "product";
        message = new HashMap<>();
        message.put("productId", productId);
        message.put("accessToken", accessToken);
        datetime = LocalDateTime.now().toString();
    }
}
