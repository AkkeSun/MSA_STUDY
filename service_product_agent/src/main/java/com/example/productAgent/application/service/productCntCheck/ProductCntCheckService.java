package com.example.productAgent.application.service.productCntCheck;

import static com.example.productAgent.infrastructure.constValue.KafkaTopic.NOTIFICATION_PRODUCT_CNT_CHECK_TOPIC;

import com.example.productAgent.application.port.in.productCntCheck.ProductCntCheckUseCase;
import com.example.productAgent.application.port.out.FindProductPort;
import com.example.productAgent.application.port.out.SendMessagePort;
import com.example.productAgent.domain.Notification;
import com.example.productAgent.domain.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ProductCntCheckService implements ProductCntCheckUseCase {

    private final FindProductPort findProductPort;

    private final SendMessagePort sendMessagePort;

    @Override
    public void productCntCheck() {
        List<Product> productList = findProductPort.findAllByLowInventory();
        if (productList.isEmpty()) {
            return;
        }

        Map<String, List<String>> messageMap = new HashMap<>();
        for (Product product : productList) {
            if (!messageMap.containsKey(product.getSeller())) {
                messageMap.put(product.getSeller(), new ArrayList<>());
            }

            String productInfo = String.format("%s (%s)", product.getName(), product.getOption());
            messageMap.get(product.getSeller()).add(productInfo);
        }

        messageMap.forEach((seller, targetProducts) -> {
            Notification notification = Notification.builder()
                .channel("#productCntCheck")
                .userId(seller)
                .message(targetProducts.toString() + " 의 상품 수량 확인이 필요합니다")
                .build();

            sendMessagePort.send(NOTIFICATION_PRODUCT_CNT_CHECK_TOPIC, notification);
        });
    }
}
