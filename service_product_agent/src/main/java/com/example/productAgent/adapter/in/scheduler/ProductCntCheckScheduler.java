package com.example.productAgent.adapter.in.scheduler;

import com.example.productAgent.application.port.in.productCntCheck.ProductCntCheckUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ProductCntCheckScheduler {

    private final ProductCntCheckUseCase productCntCheckUseCase;

    @Scheduled(fixedRate = 3600000)
    void productCntCheck() {
        productCntCheckUseCase.productCntCheck();
    }
}
