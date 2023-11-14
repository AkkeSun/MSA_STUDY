package com.example.gateway.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.server.reactive.ServerHttpRequest;

@Getter
@Setter
public class ExceptionResponse {

    private String status;
    private Map<String, String> data;

    public ExceptionResponse(GatewayException exception, ServerHttpRequest request) {
        this.status = "N";
        this.data = new HashMap<>();
        data.put("datetime", LocalDateTime.now().toString());
        data.put("path", request.getPath().toString());
        data.put("method", request.getMethodValue());
        data.put("errorCode", exception.getGatewayErrorCode().getErrorCode());
        data.put("message", exception.getGatewayErrorCode().getMessage());
    }

    public ExceptionResponse(String message, ServerHttpRequest request) {
        this.status = "N";
        this.data = new HashMap<>();
        data.put("datetime", LocalDateTime.now().toString());
        data.put("path", request.getPath().toString());
        data.put("method", request.getMethodValue());
        data.put("errorCode", "500");
        data.put("message", message);
    }
}
