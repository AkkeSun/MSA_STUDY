package com.example.productAgent.application.port.out;

public interface SendMessagePort {

    void send(String topicName, Object message);
}
