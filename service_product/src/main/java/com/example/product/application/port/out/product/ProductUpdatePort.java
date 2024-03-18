package com.example.product.application.port.out.product;

import com.example.product.application.port.in.update.ProductUpdateCommand;
import com.example.product.domain.ProductHistory;

public interface ProductUpdatePort {

    ProductHistory update(ProductUpdateCommand command, String category,
        String seller);
}
