package com.example.market.domain.product;

import com.example.market.domain.category.Category;
import com.example.market.domain.user.User;
import com.example.market.domain.wish.Wish;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")

    private long id;

    private String title;
    @Column(name = "upload_date")
    private LocalDateTime uploadDate;
    private long views; //조회수
    private String productImgUrl;
    private String text; //제품 상세
    private int price;
    private boolean locateAuthorization;
    private long wishCount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"productList"})
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private boolean isWish;


    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties({"product"})
    private List<Wish> wishList;

    @PrePersist
    public void uploadDate() {
        this.uploadDate = LocalDateTime.now();
    }

    @Builder
    public Product(String title, Category category, String productImgUrl, String text, int price, User user, boolean locateAuthorization) {
        this.title = title;
        this.category = category;
        this.productImgUrl = productImgUrl;
        this.text = text;
        this.price = price;
        this.user = user;
        this.locateAuthorization = locateAuthorization;
    }

}
