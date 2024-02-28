package com.example.product.application.port.out;

import com.example.product.domain.Product;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ProductSearchPort {
    Product findById(Integer id);

    List<Product> findBySeller(String seller, Pageable pageable);

    List<Product> findByCategory(String category, Pageable pageable);
}
