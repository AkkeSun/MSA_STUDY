package com.example.product.application.port.out.product;

import com.example.product.domain.Product;
import com.example.product.domain.ProductHistory;

public interface ProductDeletePort {

    ProductHistory delete(Product product);
}
