package com.example.product.application.port.out.productHistory;

import com.example.product.domain.ProductHistory;

public interface ProductHistorySavePort {

    void save(ProductHistory history);
}
