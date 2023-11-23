package com.example.product.adopter.out.repository;

import com.example.product.adopter.out.entity.ProductHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductHistoryRepository extends JpaRepository<ProductHistoryEntity, Integer> {

}
