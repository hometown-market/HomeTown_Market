package com.example.market.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/api/product_list/{categoryId}")
    public List<CategoryDto> subcategories(@PathVariable long categoryId) {
        return categoryService.categoryList(categoryId);
    }
}
