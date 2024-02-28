package com.example.product.application.port.out;

import com.example.product.application.port.in.update.ProductUpdateCommand;

public interface ProductUpdatePort {

    void update(ProductUpdateCommand command, String category, String seller);
}
