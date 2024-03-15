package com.example.productAgent.application.port.in.updateBuyProductCnt;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateBuyProductCntCommand {

    private Integer productId;
    
    private int buyCnt;

    @Builder
    public UpdateBuyProductCntCommand(Integer productId, int buyCnt) {
        this.productId = productId;
        this.buyCnt = buyCnt;
    }
}
