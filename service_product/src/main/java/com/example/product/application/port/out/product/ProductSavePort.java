package com.example.product.application.port.out.product;

import com.example.product.application.port.in.save.ProductSaveCommand;
import com.example.product.domain.ProductHistory;

public interface ProductSavePort {

    ProductHistory save(ProductSaveCommand command, String category, String seller);
}
