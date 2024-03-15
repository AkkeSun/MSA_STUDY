package com.example.productAgent.adapter.out.external.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    void send(String topicName, Object message) {
        try {
            ListenableFuture<SendResult<String, String>> result =
                kafkaTemplate.send(topicName, new ObjectMapper().writeValueAsString(message));

            result.addCallback(new KafkaSendCallback<>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("[{} producer] ==> {}", topicName, message);
                }

                @Override
                public void onFailure(KafkaProducerException ex) {
                    log.error("[{} producer] error ==> {} : {}",
                        topicName, message, ex.getMessage());
                }
            });
        } catch (Exception e) {
            log.error("[{} producer] outer error ==> {} : {}", topicName, message, e.getMessage());
        }
    }
}
