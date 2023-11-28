package com.example.product.application.port.out;

import com.example.product.application.port.in.ProductCommand;

public interface ProductCreatePort {
    Integer create(ProductCommand command, String category, String seller);
}
