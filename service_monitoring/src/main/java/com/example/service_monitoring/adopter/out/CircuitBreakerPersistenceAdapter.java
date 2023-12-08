package com.example.service_monitoring.adopter.out;

import static com.example.service_monitoring.infrastructure.Constants.CIRCUIT_BREAKER_LOG_COLLECTION_NAME;

import com.example.service_monitoring.applicaition.port.out.CircuitBreakerCreatePort;
import com.example.service_monitoring.domain.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CircuitBreakerPersistenceAdapter implements CircuitBreakerCreatePort {

    private final MongoTemplate mongoTemplate;

    @Override
    public void save(CircuitBreaker circuitBreaker) {
        mongoTemplate.save(circuitBreaker, CIRCUIT_BREAKER_LOG_COLLECTION_NAME);
    }
}
