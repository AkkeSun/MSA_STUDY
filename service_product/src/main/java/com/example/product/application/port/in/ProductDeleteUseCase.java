package com.example.product.application.port.in;

public interface ProductDeleteUseCase {
    ProductResponse delete(String accessToken, Integer productId);
}
