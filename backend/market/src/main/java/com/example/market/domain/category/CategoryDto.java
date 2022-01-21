package com.example.market.domain.category;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {

    private long categoryId;
    private String categoryName;
    private long parentId;
    private List<CategoryDto> subCategories;

    public CategoryDto(long categoryId, String categoryName, Long parentId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}
