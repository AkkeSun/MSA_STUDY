package com.example.productAgent.application.service.updateBuyProductCnt;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateBuyProductCntServiceResponse {

    boolean succeess;

    @Builder
    public UpdateBuyProductCntServiceResponse(boolean succeess) {
        this.succeess = succeess;
    }
}
