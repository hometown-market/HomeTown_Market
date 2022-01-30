package com.example.market.domain.category;

import com.example.market.domain.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@RequiredArgsConstructor
public class Category {

    @Id
    private Long categoryId;

    private String categoryName;
    private Long parentId;


    @OneToMany(mappedBy = "category")
    private List<Product> productList;

    public Category(Long categoryId, String categoryName, Long parentId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}
