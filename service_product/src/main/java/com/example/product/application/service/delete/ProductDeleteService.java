package com.example.product.application.service.delete;

import static com.example.product.infrastructure.exception.ErrorCode.ACCESS_DENIED;

import com.example.product.application.port.in.delete.ProductDeleteUseCase;
import com.example.product.application.port.out.AccountSearchPort;
import com.example.product.application.port.out.ProductDeletePort;
import com.example.product.application.port.out.ProductSearchPort;
import com.example.product.domain.Product;
import com.example.product.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ProductDeleteService implements ProductDeleteUseCase {

    private final AccountSearchPort accountSearchPort;

    private final ProductSearchPort productSearchPort;

    private final ProductDeletePort productDeletePort;

    @Override
    public ProductDeleteServiceResponse delete(String accessToken, Integer productId) {
        String accountId = accountSearchPort.getAccountId(accessToken);
        Product product = productSearchPort.findById(productId);
        if (!accountId.equals(product.getSeller())) {
            throw new CustomException(ACCESS_DENIED);
        }

        productDeletePort.delete(productId);
        return new ProductDeleteServiceResponse(true);
    }
}
