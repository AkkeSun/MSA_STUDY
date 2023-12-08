package com.example.service_monitoring.applicaition.port.in;

import org.apache.kafka.clients.consumer.ConsumerRecords;

public interface ServerErrorCreateUseCase {

    void saveAll(ConsumerRecords<String, String> records);
}
