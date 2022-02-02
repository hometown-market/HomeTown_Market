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
    private String categoryId;

    private String categoryName;
    private String parentId;


    @OneToMany(mappedBy = "category")
    private List<Product> productList;

    @Transient
    public List<Long> childIdList;



    public Category(String categoryId, String categoryName, String parentId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}
