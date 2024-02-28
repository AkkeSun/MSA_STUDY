package com.example.product.adopter.in.update;

import com.example.product.application.service.update.ProductUpdateServiceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class ProductUpdateResponse {

    private boolean result;

    @Builder
    ProductUpdateResponse(boolean result) {
        this.result = result;
    }

    ProductUpdateResponse of(ProductUpdateServiceResponse response) {
        return ProductUpdateResponse.builder()
            .result(response.isResult())
            .build();
    }
}
