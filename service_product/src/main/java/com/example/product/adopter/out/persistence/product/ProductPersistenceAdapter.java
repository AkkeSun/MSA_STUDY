package com.example.product.adopter.out.persistence.product;

import static com.example.product.infrastructure.exception.ErrorCode.ACCESS_DENIED;
import static com.example.product.infrastructure.exception.ErrorCode.INVALID_PRODUCT;
import static com.example.product.infrastructure.exception.ErrorCode.INVALID_UPDATE_ITEM;
import static com.example.product.infrastructure.utils.Constants.ACCOUNT_BREAKER_DEFAULT_VALUE;
import static com.example.product.infrastructure.utils.Constants.HISTORY_CREATE;
import static com.example.product.infrastructure.utils.Constants.HISTORY_DELETE;
import static com.example.product.infrastructure.utils.Constants.HISTORY_UPDATE_CATEGORY;
import static com.example.product.infrastructure.utils.Constants.HISTORY_UPDATE_INVENTORY;
import static com.example.product.infrastructure.utils.Constants.HISTORY_UPDATE_NAME;
import static com.example.product.infrastructure.utils.Constants.HISTORY_UPDATE_OPTION;
import static com.example.product.infrastructure.utils.Constants.HISTORY_UPDATE_PRICE;

import com.example.product.application.port.in.save.ProductSaveCommand;
import com.example.product.application.port.in.update.ProductUpdateCommand;
import com.example.product.application.port.out.product.ProductDeletePort;
import com.example.product.application.port.out.product.ProductSavePort;
import com.example.product.application.port.out.product.ProductSearchPort;
import com.example.product.application.port.out.product.ProductUpdatePort;
import com.example.product.domain.Product;
import com.example.product.domain.ProductHistory;
import com.example.product.infrastructure.exception.CustomException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Component
@Transactional
@RequiredArgsConstructor
class ProductPersistenceAdapter implements ProductSavePort, ProductUpdatePort,
    ProductSearchPort, ProductDeletePort {

    private final ProductRepository productRepository;

    @Override
    public ProductHistory save(ProductSaveCommand command, String category, String seller) {
        ProductEntity productEntity = ProductEntity.builder()
            .name(command.getName())
            .option(command.getOption())
            .category(category)
            .price(command.getPrice())
            .inventory(command.getCount())
            .seller(seller)
            .regDate(LocalDateTime.now())
            .build();
        productRepository.save(productEntity);
        return productEntity.toHistoryDomain(HISTORY_CREATE);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Integer id) {
        ProductEntity entity = productRepository.findById(id)
            .orElseThrow(() -> new CustomException(INVALID_PRODUCT));
        return entity.toDomain();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findBySeller(String seller, Pageable pageable) {
        return productRepository.findBySeller(seller, pageable).stream()
            .map(ProductEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCategory(String category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable).stream()
            .map(ProductEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public ProductHistory update(ProductUpdateCommand command, String category,
        String seller) {
        ProductEntity entity = productRepository.findById(command.getProductId())
            .orElseThrow(() -> new CustomException(INVALID_PRODUCT));

        if (!seller.equals(ACCOUNT_BREAKER_DEFAULT_VALUE) && !entity.getSeller().equals(seller)) {
            throw new CustomException(ACCESS_DENIED);
        }

        List<String> historyMsg = new ArrayList<>();
        if (StringUtils.hasText(command.getName())) {
            entity.updateName(command.getName());
            historyMsg.add(HISTORY_UPDATE_NAME);
        }
        if (StringUtils.hasText(command.getOption())) {
            entity.updateOption(command.getOption());
            historyMsg.add(HISTORY_UPDATE_OPTION);
        }
        if (StringUtils.hasText(category)) {
            entity.updateCategory(category);
            historyMsg.add(HISTORY_UPDATE_CATEGORY);
        }
        if (command.getPrice() != null) {
            entity.updatePrice(command.getPrice());
            historyMsg.add(HISTORY_UPDATE_PRICE);
        }
        if (command.getCount() != null) {
            entity.updateInventory(entity.getInventory() + command.getCount());
            historyMsg.add(HISTORY_UPDATE_INVENTORY);
        }
        if (historyMsg.isEmpty()) {
            throw new CustomException(INVALID_UPDATE_ITEM);
        }

        entity.setUpdateDate(LocalDateTime.now());
        return entity.toHistoryDomain(historyMsg.toString());
    }

    @Override
    public ProductHistory delete(Product product) {
        productRepository.deleteById(product.getId());
        return ProductHistory.builder()
            .productId(product.getId())
            .regDate(LocalDateTime.now())
            .description(HISTORY_DELETE)
            .build();

    }

}
