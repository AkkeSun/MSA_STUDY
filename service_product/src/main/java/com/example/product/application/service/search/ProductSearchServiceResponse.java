package com.example.product.application.service.search;

import com.example.product.domain.Product;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

@Getter
public class ProductSearchServiceResponse {

    List<Product> productList;

    public ProductSearchServiceResponse(Product product) {
        this.productList = Collections.singletonList(product);
    }

    public ProductSearchServiceResponse(List<Product> productList) {
        this.productList = productList;
    }
}
