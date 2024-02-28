package com.example.product.adopter.in.search;

import com.example.product.application.service.search.ProductSearchServiceResponse;
import com.example.product.domain.Product;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class ProductSearchResponse {

    private List<Product> productList;

    @Builder
    ProductSearchResponse(List<Product> productList) {
        this.productList = productList;
    }

    ProductSearchResponse of(ProductSearchServiceResponse response) {
        return ProductSearchResponse.builder()
            .productList(response.getProductList())
            .build();
    }
}
