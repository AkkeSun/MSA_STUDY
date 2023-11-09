package com.example.serviceUser.infrastructure.exception;

import static com.example.serviceUser.infrastructure.exception.ApiErrorCode.ACCESS_DENIED;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                return new ApiException(ACCESS_DENIED);
            case 404:
                break;
            default:
                return new RuntimeException("Api Call Error : " + response.request().url());
        }
        return null;
    }
}
