package com.example.product.infrastructure.exception;

import static com.example.product.infrastructure.utils.Constants.SERVICE_ERROR_TOPIC;

import com.example.product.application.port.in.ProductResponse;
import com.example.product.application.port.out.KafkaPort;
import com.example.product.infrastructure.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExceptionService {

    private final JsonUtil jsonUtil;
    private final KafkaPort kafkaPort;

    public ProductResponse getExceptionDTO(RuntimeException e) {
        ExceptionResponse response = e instanceof ApiException ?
            new ExceptionResponse((ApiException) e) : new ExceptionResponse(e.getMessage());
        kafkaPort.sendMsg(SERVICE_ERROR_TOPIC, jsonUtil.objectToJson(response));
        return ProductResponse.ofFail(response);
    }
}
