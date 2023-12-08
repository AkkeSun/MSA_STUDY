package com.example.service_monitoring.applicaition.port.in;

public interface CircuitBreakerCreateUseCase {

    void save(String kafkaMessage);
}
