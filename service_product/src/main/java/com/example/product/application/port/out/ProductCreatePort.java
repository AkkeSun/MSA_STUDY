package com.example.product.application.port.out;

import com.example.product.application.port.in.ProductCommand;

public interface ProductCreatePort {
    void create(ProductCommand command, String category, String seller);
}
