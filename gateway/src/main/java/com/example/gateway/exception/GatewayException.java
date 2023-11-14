package com.example.gateway.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GatewayException extends RuntimeException {

    private final GatewayErrorCode gatewayErrorCode;
}
