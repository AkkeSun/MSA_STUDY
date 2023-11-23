package com.example.product.application.port.out;

import com.example.product.application.port.in.ProductCommand;

public interface ProductUpdatePort {
    String update(ProductCommand command, String category, String seller);
}
