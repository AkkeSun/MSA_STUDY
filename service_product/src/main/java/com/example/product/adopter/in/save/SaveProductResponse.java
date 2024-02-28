package com.example.product.adopter.in.save;

import com.example.product.application.service.save.ProductSaveServiceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class SaveProductResponse {

    private boolean result;

    @Builder
    SaveProductResponse(boolean result) {
        this.result = result;
    }

    SaveProductResponse of(ProductSaveServiceResponse response) {
        return SaveProductResponse.builder()
            .result(response.isResult())
            .build();
    }
}
