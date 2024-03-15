package com.example.productAgent.adapter.out.persistence.product;

import com.example.productAgent.application.port.in.updateBuyProductCnt.UpdateBuyProductCntCommand;
import com.example.productAgent.application.port.out.FindProductPort;
import com.example.productAgent.application.port.out.UpdateBuyProductCntPort;
import com.example.productAgent.domain.Product;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
class ProductPersistenceAdapter implements FindProductPort, UpdateBuyProductCntPort {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllByLowInventory() {
        return productRepository.findByInventoryBefore(15).stream()
            .map(ProductEntity::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public boolean updateBuyProductCnt(UpdateBuyProductCntCommand command) {
        Optional<ProductEntity> optional = productRepository.findById(command.getProductId());
        if (optional.isEmpty()) {
            return false;
        }

        ProductEntity entity = optional.get();
        entity.updateBuyCnt(command.getBuyCnt());
        return true;
    }
}
