package com.example.product.adopter.out.persistence;

import static com.example.product.infrastructure.exception.ErrorCode.ACCESS_DENIED;
import static com.example.product.infrastructure.exception.ErrorCode.INVALID_PRODUCT;
import static com.example.product.infrastructure.exception.ErrorCode.INVALID_UPDATE_ITEM;
import static com.example.product.infrastructure.utils.Constants.ACCOUNT_BREAKER_DEFAULT_VALUE;
import static com.example.product.infrastructure.utils.Constants.HISTORY_CATEGORY;
import static com.example.product.infrastructure.utils.Constants.HISTORY_CREATE;
import static com.example.product.infrastructure.utils.Constants.HISTORY_INVENTORY;
import static com.example.product.infrastructure.utils.Constants.HISTORY_NAME;
import static com.example.product.infrastructure.utils.Constants.HISTORY_OPTION;
import static com.example.product.infrastructure.utils.Constants.HISTORY_PRICE;

import com.example.product.application.port.in.save.ProductSaveCommand;
import com.example.product.application.port.in.update.ProductUpdateCommand;
import com.example.product.application.port.out.ProductDeletePort;
import com.example.product.application.port.out.ProductSavePort;
import com.example.product.application.port.out.ProductSearchPort;
import com.example.product.application.port.out.ProductUpdatePort;
import com.example.product.domain.Product;
import com.example.product.infrastructure.exception.CustomException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
class ProductPersistenceAdapter implements ProductSavePort, ProductUpdatePort,
    ProductSearchPort, ProductDeletePort {

    private final ProductHistoryRepository historyRepository;

    private final ProductRepository productRepository;

    @Override
    public Integer save(ProductSaveCommand command, String category, String seller) {
        LocalDateTime now = LocalDateTime.now();
        ProductHistoryEntity historyEntity = ProductHistoryEntity.builder()
            .description(HISTORY_CREATE)
            .regDate(now)
            .build();
        ProductEntity productEntity = ProductEntity.builder()
            .name(command.getName())
            .option(command.getOption())
            .category(category)
            .price(command.getPrice())
            .inventory(command.getCount())
            .seller(seller)
            .productHistoryEntities(new ArrayList<>())
            .regDate(now)
            .build();
        historyEntity.setRelation(productEntity);
        productRepository.save(productEntity);
        return productEntity.getId();
    }

    @Override
    public Product findById(Integer id) {
        ProductEntity entity = productRepository.findById(id)
            .orElseThrow(() -> new CustomException(INVALID_PRODUCT));
        return Product.builder()
            .id(entity.getId())
            .name(entity.getName())
            .option(entity.getOption())
            .category(entity.getCategory())
            .price(entity.getPrice())
            .inventory(entity.getInventory())
            .seller(entity.getSeller())
            .build();
    }

    @Override
    public List<Product> findBySeller(String seller, Pageable pageable) {
        return productRepository.findBySeller(seller, pageable).stream()
            .map(entity -> Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .option(entity.getOption())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .inventory(entity.getInventory())
                .seller(entity.getSeller())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<Product> findByCategory(String category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable).stream()
            .map(entity -> Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .option(entity.getOption())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .inventory(entity.getInventory())
                .seller(entity.getSeller())
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(ProductUpdateCommand command, String category, String seller) {
        ProductEntity entity = productRepository.findById(command.getProductId())
            .orElseThrow(() -> new CustomException(INVALID_PRODUCT));

        if (!seller.equals(ACCOUNT_BREAKER_DEFAULT_VALUE) && !entity.getSeller().equals(seller)) {
            throw new CustomException(ACCESS_DENIED);
        }

        List<String> historyMsg = new ArrayList<>();
        if (StringUtils.hasText(command.getName())) {
            entity.setName(command.getName());
            historyMsg.add(HISTORY_NAME);
        }
        if (StringUtils.hasText(command.getOption())) {
            entity.setOption(command.getOption());
            historyMsg.add(HISTORY_OPTION);
        }
        if (StringUtils.hasText(category)) {
            entity.setCategory(category);
            historyMsg.add(HISTORY_CATEGORY);
        }
        if (command.getPrice() != null) {
            entity.setPrice(command.getPrice());
            historyMsg.add(HISTORY_PRICE);
        }
        if (command.getCount() != null) {
            entity.setId(entity.getInventory() + command.getCount());
            historyMsg.add(HISTORY_INVENTORY);
        }
        if (historyMsg.isEmpty()) {
            throw new CustomException(INVALID_UPDATE_ITEM);
        }

        LocalDateTime updateDate = LocalDateTime.now();
        entity.setUpdateDate(updateDate);
        historyRepository.save(ProductHistoryEntity.builder()
            .description(historyMsg.toString())
            .regDate(updateDate)
            .productEntity(entity)
            .build());
    }

    @Override
    public void delete(Integer productId) {
        productRepository.deleteById(productId);
    }

}
