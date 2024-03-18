package com.example.product.application.service.save;

import static com.example.product.infrastructure.utils.Constants.ACCOUNT_BREAKER_DEFAULT_VALUE;

import com.example.product.application.port.in.save.ProductSaveCommand;
import com.example.product.application.port.in.save.ProductSaveUseCase;
import com.example.product.application.port.out.account.AccountSearchPort;
import com.example.product.application.port.out.category.CategorySearchPort;
import com.example.product.application.port.out.product.ProductSavePort;
import com.example.product.application.port.out.productHistory.ProductHistorySavePort;
import com.example.product.domain.ProductHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
class ProductSaveService implements ProductSaveUseCase {

    private final ProductSavePort productSavePort;

    private final ProductHistorySavePort productHistorySavePort;

    private final CategorySearchPort categorySearchPort;

    private final AccountSearchPort accountSearchPort;

    @Override
    @Transactional
    public ProductSaveServiceResponse save(ProductSaveCommand command, String accessToken) {
        String categoryName = categorySearchPort.getCategoryName(command.getCategoryId());
        String userId = accountSearchPort.getAccountId(accessToken);

        ProductHistory history = productSavePort.save(command, categoryName, userId);
        productHistorySavePort.save(history);

        if (userId.equals(ACCOUNT_BREAKER_DEFAULT_VALUE)) {
            log.error("[ACCOUNT_BREAKER_RUN] {} - {}", history.getProductId(), accessToken);
            // TODO: send log save topic & skack noti
        }
        return new ProductSaveServiceResponse(true);
    }
}
