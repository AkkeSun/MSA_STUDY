package com.example.productAgent.adapter.out.external.producer;

import com.example.productAgent.application.port.out.SendMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageAdapter implements SendMessagePort {

    private final MessageProducer messageProducer;

    @Override
    public void send(String topic, Object message) {
        messageProducer.send(topic, message);
    }
}
