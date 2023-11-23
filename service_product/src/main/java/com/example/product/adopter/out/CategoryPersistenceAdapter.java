package com.example.product.adopter.out;

import static com.example.product.infrastructure.exception.ApiErrorCode.INVALID_CATEGORY;

import com.example.product.adopter.out.entity.CategoryEntity;
import com.example.product.adopter.out.repository.CategoryRepository;
import com.example.product.application.port.out.CategoryReadPort;
import com.example.product.infrastructure.exception.ApiException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryReadPort {

    private final CategoryRepository categoryRepository;

    @Override
    public String getCategoryName(Integer id) {
        List<CategoryEntity> list = getCategoryList().stream()
            .filter(entity -> entity.getId().equals(id))
            .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new ApiException(INVALID_CATEGORY);
        }
        return list.get(0).getName();
    }

    /*
    TODO: 캐시처리
    */
    @Transactional(readOnly = true)
    public List<CategoryEntity> getCategoryList() {
        return categoryRepository.findAll();
    }
}
