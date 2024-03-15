package com.example.productAgent.adapter.out.persistence.product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByInventoryBefore(int cnt);
}
