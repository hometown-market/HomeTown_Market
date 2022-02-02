package com.example.market.domain.product.dto;

import lombok.Data;

@Data
public class ProductCategoryCountDto {
    private String categoryId;
    private Long count;

    public ProductCategoryCountDto(String categoryId, Long count) {
        this.categoryId = categoryId;
        this.count = count;
    }
}
