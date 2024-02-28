package com.example.product.application.service.update;

import com.example.product.application.port.in.update.ProductUpdateCommand;
import com.example.product.application.port.in.update.ProductUpdateUseCase;
import com.example.product.application.port.out.AccountSearchPort;
import com.example.product.application.port.out.CategorySearchPort;
import com.example.product.application.port.out.ProductUpdatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ProductUpdateService implements ProductUpdateUseCase {

    private final ProductUpdatePort productUpdatePort;

    private final CategorySearchPort categorySearchPort;

    private final AccountSearchPort accountSearchPort;
    
    @Override
    public ProductUpdateServiceResponse update(ProductUpdateCommand command, String accessToken) {
        String category = null;
        if (command.getCategoryId() != null) {
            category = categorySearchPort.getCategoryName(command.getCategoryId());
        }

        String accountId = accountSearchPort.getAccountId(accessToken);
        productUpdatePort.update(command, category, accountId);
        return new ProductUpdateServiceResponse(true);
    }
}
