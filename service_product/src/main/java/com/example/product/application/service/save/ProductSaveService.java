package com.example.product.application.service.save;

import static com.example.product.infrastructure.utils.Constants.ACCOUNT_BREAKER_DEFAULT_VALUE;

import com.example.product.application.port.in.save.ProductSaveCommand;
import com.example.product.application.port.in.save.ProductSaveUseCase;
import com.example.product.application.port.out.AccountSearchPort;
import com.example.product.application.port.out.CategorySearchPort;
import com.example.product.application.port.out.ProductSavePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class ProductSaveService implements ProductSaveUseCase {

    private final ProductSavePort productSavePort;

    private final CategorySearchPort categorySearchPort;

    private final AccountSearchPort accountSearchPort;

    @Override
    public ProductSaveServiceResponse save(ProductSaveCommand command, String accessToken) {
        String categoryName = categorySearchPort.getCategoryName(command.getCategoryId());
        String userId = accountSearchPort.getAccountId(accessToken);
        Integer productId = productSavePort.save(command, categoryName, userId);

        if (userId.equals(ACCOUNT_BREAKER_DEFAULT_VALUE)) {
            log.error("[ACCOUNT_BREAKER_RUN] {} - {}", productId, accessToken);
            // TODO: send log save topic & skack noti
        }
        return new ProductSaveServiceResponse(true);
    }
}
