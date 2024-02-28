package com.example.product.application.service.search;

import com.example.product.application.port.in.search.ProductSearchUseCase;
import com.example.product.application.port.out.CategorySearchPort;
import com.example.product.application.port.out.ProductSearchPort;
import com.example.product.domain.Product;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ProductSearchService implements ProductSearchUseCase {

    private final ProductSearchPort productSearchPort;

    private final CategorySearchPort categorySearchPort;

    @Override
    public ProductSearchServiceResponse findById(Integer productId) {
        Product product = productSearchPort.findById(productId);
        return new ProductSearchServiceResponse(product);
    }

    @Override
    public ProductSearchServiceResponse findBySeller(String seller, Pageable pageable) {
        List<Product> productList = productSearchPort.findBySeller(seller, pageable);
        return new ProductSearchServiceResponse(productList);
    }

    @Override
    public ProductSearchServiceResponse findByCategory(Integer categoryId, Pageable pageable) {
        String category = categorySearchPort.getCategoryName(categoryId);
        List<Product> productList = productSearchPort.findByCategory(category, pageable);
        return new ProductSearchServiceResponse(productList);
    }
}
