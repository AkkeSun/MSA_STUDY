package com.example.product.application.port.out;

public interface KafkaPort {

    void sendMsg(String topic, String message);
}
