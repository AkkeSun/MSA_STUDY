package com.example.productAgent.application.port.in.updateBuyProductCnt;

import com.example.productAgent.application.service.updateBuyProductCnt.UpdateBuyProductCntServiceResponse;

public interface UpdateBuyProductCntUseCase {

    UpdateBuyProductCntServiceResponse updateBuyProductCnt(String message);
}
