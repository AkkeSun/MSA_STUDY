package com.example.product.application.port.in;

public interface ProductUpdateUseCase {

    ProductResponse update(ProductCommand command, String accessToken);
}
