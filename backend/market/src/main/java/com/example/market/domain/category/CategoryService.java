package com.example.market.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto categoryParent() {
        Map<Long, List<CategoryDto>> categoryGroup = new HashMap<>();
        for (Category c : categoryRepository.findAll()) {
            CategoryDto categories = new CategoryDto(c.getCategoryId(), c.getCategoryName(), c.getParentId());
            categoryGroup.computeIfAbsent(categories.getParentId(), k -> new ArrayList<>()).add(categories);
        }

        CategoryDto categoryP = new CategoryDto(00l, "START", null);
        addSubCategories(categoryP, categoryGroup);

        return categoryP;
    }

    private void addSubCategories(CategoryDto parent, Map<Long, List<CategoryDto>> groupingByParentId) {
        List<CategoryDto> subCategories = groupingByParentId.get(parent.getCategoryId());

        if(subCategories == null) {
            return;
        }

        parent.setSubCategories(subCategories);

        for (CategoryDto s : subCategories) {
            addSubCategories(s, groupingByParentId);
        }
    }

}
