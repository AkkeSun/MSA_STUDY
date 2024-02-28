package com.example.product.adopter.in.delete;

import com.example.product.application.service.delete.ProductDeleteServiceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class ProductDeleteResponse {

    private boolean result;

    @Builder
    ProductDeleteResponse(boolean result) {
        this.result = result;
    }

    ProductDeleteResponse of(ProductDeleteServiceResponse response) {
        return ProductDeleteResponse.builder()
            .result(response.isResult())
            .build();
    }
}
