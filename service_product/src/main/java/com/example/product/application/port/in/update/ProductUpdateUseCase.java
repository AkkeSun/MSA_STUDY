package com.example.product.application.port.in.update;

import com.example.product.application.service.update.ProductUpdateServiceResponse;

public interface ProductUpdateUseCase {

    ProductUpdateServiceResponse update(ProductUpdateCommand command, String accessToken);
}
