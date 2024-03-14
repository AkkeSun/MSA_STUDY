package com.example.product.application.service.update;

import com.example.product.application.port.in.update.ProductUpdateCommand;
import com.example.product.application.port.in.update.ProductUpdateUseCase;
import com.example.product.application.port.out.account.AccountSearchPort;
import com.example.product.application.port.out.category.CategorySearchPort;
import com.example.product.application.port.out.product.ProductUpdatePort;
import com.example.product.application.port.out.productHistory.ProductHistorySavePort;
import com.example.product.domain.ProductHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class ProductUpdateService implements ProductUpdateUseCase {

    private final ProductUpdatePort productUpdatePort;

    private final ProductHistorySavePort productHistorySavePort;

    private final CategorySearchPort categorySearchPort;

    private final AccountSearchPort accountSearchPort;

    @Override
    @Transactional
    public ProductUpdateServiceResponse update(ProductUpdateCommand command, String accessToken) {
        String category = null;
        if (command.getCategoryId() != null) {
            category = categorySearchPort.getCategoryName(command.getCategoryId());
        }

        String accountId = accountSearchPort.getAccountId(accessToken);
        ProductHistory history = productUpdatePort.update(command, category, accountId);
        productHistorySavePort.save(history);

        return new ProductUpdateServiceResponse(true);
    }
}
