package com.example.product.adopter.out;

import com.example.product.application.port.out.KafkaPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaAdapter implements KafkaPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMsg(String topic, String message) {
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, message);
        result.addCallback(new KafkaSendCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("[kafka producer] {} -- {}", topic, message);
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                log.error("[kafka producer] {} failed -- {} | {}",
                    topic, message, ex.toString());
                // TODO: push slack
            }
        });
    }
}
