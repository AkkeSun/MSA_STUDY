package com.example.product.application.service;

import static com.example.product.infrastructure.exception.ApiErrorCode.ACCESS_DENIED;

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
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        productCreatePort.create(command, category, getUserId(accessToken));
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
        String updateItem = productUpdatePort.update(command, category, getUserId(accessToken));
        return ProductResponse.ofSuccess(updateItem);
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
        Map<String, String> account = (LinkedHashMap<String, String>) accountReadPort.getAccount(
            accessToken).getData();
        return account.get("userId");
    }
}
