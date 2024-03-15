package com.example.productAgent.application.port.out;

import com.example.productAgent.application.port.in.updateBuyProductCnt.UpdateBuyProductCntCommand;

public interface UpdateBuyProductCntPort {

    boolean updateBuyProductCnt(UpdateBuyProductCntCommand command);
}
