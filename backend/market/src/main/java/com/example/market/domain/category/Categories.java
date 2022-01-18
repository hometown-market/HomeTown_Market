package com.example.market.domain.category;

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
public class Categories {

    @Id
    @GeneratedValue
    private long categoryId;

    private String categoryName;
    private long parentId;

    @OneToMany
    private List<Categories> subCategories;

    public Categories(long categoryId, String categoryName, Long parentId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}
