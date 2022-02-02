package com.example.market.domain.category;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {

    private String categoryId;
    private String categoryName;
    private String parentId;
    private List<CategoryDto> subCategories;

    public CategoryDto(String categoryId, String categoryName, String parentId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}