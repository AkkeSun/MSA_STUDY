package com.example.product.application.port.in.delete;

import com.example.product.application.service.delete.ProductDeleteServiceResponse;

public interface ProductDeleteUseCase {

    ProductDeleteServiceResponse delete(String accessToken, Integer productId);
}
