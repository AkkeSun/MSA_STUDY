package com.example.productAgent.adapter.in.consumer;

import com.example.productAgent.application.port.in.updateBuyProductCnt.UpdateBuyProductCntUseCase;
import com.example.productAgent.application.service.updateBuyProductCnt.UpdateBuyProductCntServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class UpdateProductCntConsumer {

    private final UpdateBuyProductCntUseCase updateBuyProductCntUseCase;

    @KafkaListener(topics = "buyProductCntUpdate", containerFactory = "listenerContainerFactory", concurrency = "3")
    public void consumer1(@Payload String message, Acknowledgment acknowledgment) {
        log.info("[BuyProductCntUpdate consumer] <== " + message);

        UpdateBuyProductCntServiceResponse response =
            updateBuyProductCntUseCase.updateBuyProductCnt(message);
        if (response.isSucceess()) {
            acknowledgment.acknowledge();
        }
    }
}
