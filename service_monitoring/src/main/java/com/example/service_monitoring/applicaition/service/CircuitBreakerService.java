package com.example.service_monitoring.applicaition.service;

import com.example.service_monitoring.applicaition.port.in.CircuitBreakerCreateUseCase;
import com.example.service_monitoring.applicaition.port.out.CircuitBreakerCreatePort;
import com.example.service_monitoring.domain.CircuitBreaker;
import com.example.service_monitoring.infrastructure.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CircuitBreakerService implements CircuitBreakerCreateUseCase {

    private final CircuitBreakerCreatePort circuitBreakerCreatePort;
    private final JsonUtil jsonUtil;

    @Override
    public void save(String kafkaMessage) {
        CircuitBreaker circuitBreaker = jsonUtil.jsonToObject(kafkaMessage, CircuitBreaker.class);
        circuitBreaker.setDatetime();
        circuitBreakerCreatePort.save(circuitBreaker);
    }
}
