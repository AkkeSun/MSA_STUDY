package com.example.productAgent.application.service.updateBuyProductCnt;

import static com.example.productAgent.infrastructure.constValue.KafkaTopic.SERVER_ERROR_TOPIC;
import static com.example.productAgent.infrastructure.exception.ErrorCode.INVALID_MESSAGE_TYPE;
import static com.example.productAgent.infrastructure.exception.ErrorCode.INVALID_PRODUCT_ID;

import com.example.productAgent.application.port.in.updateBuyProductCnt.UpdateBuyProductCntCommand;
import com.example.productAgent.application.port.in.updateBuyProductCnt.UpdateBuyProductCntUseCase;
import com.example.productAgent.application.port.out.SendMessagePort;
import com.example.productAgent.application.port.out.UpdateBuyProductCntPort;
import com.example.productAgent.domain.ServerError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class UpdateBuyProductCntService implements UpdateBuyProductCntUseCase {

    @Value("${spring.application.name}")
    private String SERVICE_NAME;

    private final UpdateBuyProductCntPort updateBuyProductCntPort;

    private final SendMessagePort sendMessagePort;

    @Override
    public UpdateBuyProductCntServiceResponse updateBuyProductCnt(String message) {

        try {
            UpdateBuyProductCntCommand command = new ObjectMapper().readValue(
                message, UpdateBuyProductCntCommand.class);
            boolean success = updateBuyProductCntPort.updateBuyProductCnt(command);

            if (!success) {
                ServerError serverError = ServerError.builder()
                    .service(SERVICE_NAME)
                    .method("updateBuyProductCnt")
                    .code(INVALID_PRODUCT_ID.getCode())
                    .message(INVALID_PRODUCT_ID.getMessage() + " - " + command.getProductId())
                    .regDate(LocalDateTime.now())
                    .build();
                sendMessagePort.send(SERVER_ERROR_TOPIC, serverError);
                return UpdateBuyProductCntServiceResponse.builder().succeess(false).build();
            }

            return UpdateBuyProductCntServiceResponse.builder().succeess(true).build();

        } catch (JsonProcessingException e) {
            log.error("[SERVICE] " + e.getMessage());
            ServerError serverError = ServerError.builder()
                .service(SERVICE_NAME)
                .method("updateBuyProductCnt")
                .code(INVALID_MESSAGE_TYPE.getCode())
                .message(INVALID_MESSAGE_TYPE.getMessage() + " - " + message)
                .regDate(LocalDateTime.now())
                .build();
            sendMessagePort.send(SERVER_ERROR_TOPIC, serverError);
            return UpdateBuyProductCntServiceResponse.builder().succeess(false).build();
        }
    }
}
