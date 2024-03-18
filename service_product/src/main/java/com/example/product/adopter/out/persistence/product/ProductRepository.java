package com.example.product.adopter.out.persistence.product;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Transactional(readOnly = true)
    List<ProductEntity> findBySeller(String seller, Pageable pageable);

    @Transactional(readOnly = true)
    List<ProductEntity> findByCategory(String category, Pageable pageable);
}
