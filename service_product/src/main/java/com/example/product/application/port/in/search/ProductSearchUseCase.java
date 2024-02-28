package com.example.product.application.port.in.search;

import com.example.product.application.service.search.ProductSearchServiceResponse;
import org.springframework.data.domain.Pageable;

public interface ProductSearchUseCase {

    ProductSearchServiceResponse findById(Integer productId);

    ProductSearchServiceResponse findBySeller(String seller, Pageable pageable);

    ProductSearchServiceResponse findByCategory(Integer categoryId, Pageable pageable);
}
