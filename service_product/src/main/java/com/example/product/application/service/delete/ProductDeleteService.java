package com.example.product.application.service.delete;

import static com.example.product.infrastructure.exception.ErrorCode.ACCESS_DENIED;

import com.example.product.application.port.in.delete.ProductDeleteUseCase;
import com.example.product.application.port.out.account.AccountSearchPort;
import com.example.product.application.port.out.product.ProductDeletePort;
import com.example.product.application.port.out.product.ProductSearchPort;
import com.example.product.application.port.out.productHistory.ProductHistorySavePort;
import com.example.product.domain.Product;
import com.example.product.domain.ProductHistory;
import com.example.product.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class ProductDeleteService implements ProductDeleteUseCase {

    private final AccountSearchPort accountSearchPort;

    private final ProductSearchPort productSearchPort;

    private final ProductDeletePort productDeletePort;

    private final ProductHistorySavePort productHistorySavePort;

    @Override
    @Transactional
    public ProductDeleteServiceResponse delete(String accessToken, Integer productId) {
        String accountId = accountSearchPort.getAccountId(accessToken);
        Product product = productSearchPort.findById(productId);
        if (!accountId.equals(product.getSeller())) {
            throw new CustomException(ACCESS_DENIED);
        }

        ProductHistory history = productDeletePort.delete(product);
        productHistorySavePort.save(history);
        return new ProductDeleteServiceResponse(true);
    }
}
