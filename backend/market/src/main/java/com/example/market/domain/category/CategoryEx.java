package com.example.market.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class CategoryEx {

    private final CategoryRepository categoryRepository;

    public CategoryEx(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
        ex();
    }

    private void ex() {
        Category category1 = new Category(10L, "test1", 0L);
        Category category2 = new Category(10L, "test2", 0L);
        Category category3 = new Category(1010L, "test1-1", 10L);
        Category category4 = new Category(1010L, "test1-2", 10L);
        Category category5 = new Category(101010L, "test1-1-1", 1010L);
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);
    }
}
