package com.example.product.application.port.out;

import com.example.product.application.port.in.save.ProductSaveCommand;

public interface ProductSavePort {

    Integer save(ProductSaveCommand command, String category, String seller);
}
