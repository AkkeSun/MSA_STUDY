package com.example.product.adopter.out;

import static com.example.product.infrastructure.exception.ErrorCode.INVALID_CATEGORY;

import com.example.product.adopter.out.entity.CategoryEntity;
import com.example.product.adopter.out.repository.CategoryRepository;
import com.example.product.application.port.out.CategorySearchPort;
import com.example.product.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
