package com.example.product.adopter.out.persistence.productHistory;

import com.example.product.application.port.out.productHistory.ProductHistorySavePort;
import com.example.product.domain.ProductHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ProductHistoryPersistenceAdapter implements ProductHistorySavePort {

    private final ProductHistoryRepository productHistoryRepository;

    @Override
    public void save(ProductHistory history) {
        productHistoryRepository.save(new ProductHistoryEntity().of(history));
    }
}
