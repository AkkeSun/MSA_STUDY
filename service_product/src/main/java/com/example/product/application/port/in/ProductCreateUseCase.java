package com.example.product.application.port.in;

public interface ProductCreateUseCase {

    ProductResponse save(ProductCommand command, String accessToken);
}
