package com.example.product.application.port.in.save;

import com.example.product.application.service.save.ProductSaveServiceResponse;

public interface ProductSaveUseCase {

    ProductSaveServiceResponse save(ProductSaveCommand command, String accessToken);
}
