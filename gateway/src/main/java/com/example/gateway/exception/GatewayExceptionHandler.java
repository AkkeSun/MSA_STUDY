package com.example.gateway.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Order(-1)
@Component
@RequiredArgsConstructor
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper mapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        byte[] bytes = getErrorJson(exchange, ex).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }
    
    private String getErrorJson(ServerWebExchange exchange, Throwable ex) {

        ExceptionResponse response;
        ServerHttpRequest request = exchange.getRequest();
        if (ex.getClass() == GatewayException.class) {
            response = new ExceptionResponse((GatewayException) ex, request);
        } else {
            response = new ExceptionResponse(ex.toString(), request);
        }

        try {
            return mapper.writeValueAsString(response);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "";
        }
    }
}