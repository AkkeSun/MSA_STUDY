package com.example.product.application.port.in;

import org.springframework.data.domain.Pageable;

public interface ProductSearchUseCase {

    ProductResponse findById(Integer productId);

    ProductResponse findBySeller(String seller, Pageable pageable);

    ProductResponse findByCategory(Integer categoryId, Pageable pageable);
}
