package com.example.service_monitoring.applicaition.port.out;

import com.example.service_monitoring.domain.CircuitBreaker;

public interface CircuitBreakerCreatePort {

    void save(CircuitBreaker circuitBreaker);
}
