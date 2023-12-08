package com.example.service_monitoring.adopter.in;

import com.example.service_monitoring.applicaition.port.in.CircuitBreakerCreateUseCase;
import com.example.service_monitoring.applicaition.port.in.ServerErrorCreateUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MonitoringConsumer {

    private final ServerErrorCreateUseCase serverErrorCreateUseCase;
    private final CircuitBreakerCreateUseCase circuitBreakerCreateUseCase;

    @KafkaListener(topics = "service_error", containerFactory = "batchListenerFactory")
    public void saveServiceError(ConsumerRecords<String, String> records,
        Acknowledgment acknowledgment) {
        try {
            serverErrorCreateUseCase.saveAll(records);
            acknowledgment.acknowledge(); // manual commit
        } catch (Exception e) {
            // TODO: slackUtil
        }
    }

    // concurrency = thread 수 (파티션 수와 일치시키는게 가장 이상적이다)
    @KafkaListener(topics = "od", containerFactory = "basicListenerFactory", concurrency = "2")
    public void saveCircuitBreakerLog(@Payload String message, Acknowledgment acknowledgment) {
        try {
            circuitBreakerCreateUseCase.save(message);
            acknowledgment.acknowledge(); // manual commit
        } catch (Exception e) {
            // TODO: slackUtil
        }
    }

}
