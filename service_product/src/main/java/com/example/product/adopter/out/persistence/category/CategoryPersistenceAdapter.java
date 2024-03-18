package com.example.product.adopter.out.persistence.category;

import static com.example.product.infrastructure.exception.ErrorCode.INVALID_CATEGORY;

import com.example.product.application.port.out.category.CategorySearchPort;
import com.example.product.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
class CategoryPersistenceAdapter implements CategorySearchPort {

    private final CategoryRepository categoryRepository;

    /*
        TODO: 캐시처리
    */
    @Override
    @Transactional(readOnly = true)
    public String getCategoryName(Integer id) {
        CategoryEntity entity = categoryRepository.findById(id)
            .orElseThrow(() -> new CustomException(INVALID_CATEGORY));
        return entity.getName();
    }
}
