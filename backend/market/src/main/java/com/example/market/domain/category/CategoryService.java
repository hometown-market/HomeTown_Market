package com.example.market.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDto> categoryList(long categoryId) {
        List<CategoryDto> categoryGroup = new ArrayList<>();
        for (Category c : categoryRepository.findAll()) {
            CategoryDto categories = new CategoryDto(c.getCategoryId(), c.getCategoryName(), c.getParentId());
            if(categories.getParentId() == categoryId) {
                categoryGroup.add(categories);

            }
        }
        return categoryGroup;

    }
}



