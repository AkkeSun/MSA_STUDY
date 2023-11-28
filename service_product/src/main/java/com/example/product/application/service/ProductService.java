package com.example.product.application.service;

import static com.example.product.infrastructure.exception.ApiErrorCode.ACCESS_DENIED;
import static com.example.product.infrastructure.utils.Constants.ACCOUNT_BREAKER_DEFAULT_VALUE;

import com.example.product.application.port.in.ProductCommand;
import com.example.product.application.port.in.ProductCreateUseCase;
import com.example.product.application.port.in.ProductDeleteUseCase;
import com.example.product.application.port.in.ProductResponse;
import com.example.product.application.port.in.ProductSearchUseCase;
import com.example.product.application.port.in.ProductUpdateUseCase;
import com.example.product.application.port.out.AccountReadPort;
import com.example.product.application.port.out.CategoryReadPort;
import com.example.product.application.port.out.ProductCreatePort;
import com.example.product.application.port.out.ProductDeletePort;
import com.example.product.application.port.out.ProductReadPort;
import com.example.product.application.port.out.ProductUpdatePort;
import com.example.product.domain.Product;
import com.example.product.infrastructure.exception.ApiException;
import java.util.LinkedHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService implements ProductCreateUseCase, ProductUpdateUseCase,
    ProductSearchUseCase, ProductDeleteUseCase {

    private final ProductCreatePort productCreatePort;
    private final ProductUpdatePort productUpdatePort;
    private final ProductReadPort productReadPort;
    private final ProductDeletePort productDeletePort;
    private final CategoryReadPort categoryReadPort;
    private final AccountReadPort accountReadPort;

    @Override
    public ProductResponse save(ProductCommand command, String accessToken) {
        command.validation();
        String category = categoryReadPort.getCategoryName(command.getCategoryId());
        String userId = getUserId(accessToken);
        Integer productId = productCreatePort.create(command, category, userId);
        if (userId.equals(ACCOUNT_BREAKER_DEFAULT_VALUE)) {
            log.error("[ACCOUNT_BREAKER_RUN] {} - {}", productId, accessToken);
            // TODO: send log save topic & skack noti
        }
        return ProductResponse.ofSuccess(command.getName());
    }

    @Override
    public ProductResponse findById(Integer productId) {
        return ProductResponse.ofSuccess(productReadPort.findById(productId));
    }

    @Override
    public ProductResponse findBySeller(String seller, Pageable pageable) {
        return ProductResponse.ofSuccess(productReadPort.findBySeller(seller, pageable));
    }

    @Override
    public ProductResponse findByCategory(Integer categoryId, Pageable pageable) {
        String category = categoryReadPort.getCategoryName(categoryId);
        return ProductResponse.ofSuccess(productReadPort.findByCategory(category, pageable));
    }

    @Override
    public ProductResponse update(ProductCommand command, String accessToken) {
        command.updateValidation();
        String category = null;
        if (command.getCategoryId() != null) {
            category = categoryReadPort.getCategoryName(command.getCategoryId());
        }
        String userId = getUserId(accessToken);
        return ProductResponse.ofSuccess(productUpdatePort.update(command, category, userId));
    }

    @Override
    public ProductResponse delete(String accessToken, Integer productId) {
        String userId = getUserId(accessToken);
        Product product = productReadPort.findById(productId);
        if(!userId.equals(product.getSeller())) {
            throw new ApiException(ACCESS_DENIED);
        }
        productDeletePort.delete(productId);
        return ProductResponse.ofSuccess(productId);
    }

    private String getUserId(String accessToken) {
        ProductResponse response = accountReadPort.getAccount(accessToken);
        return ((LinkedHashMap<String, String>) accountReadPort.getAccount(
            accessToken).getData()).get("userId");
    }

}
