package com.example.market.domain.product.dto;

import com.example.market.domain.category.Category;
import com.example.market.domain.product.Product;
import com.example.market.domain.product.ProductStatus;
import com.example.market.domain.user.User;
import lombok.Builder;
import lombok.Data;


@Data
public class ProductUploadDto {

    private String title;
    private String text;
    private int price;
    private boolean locateAuthorization;
    private String location;
    private User uploadUser;
    private String productImgUrl;
    private Category category;
    private ProductStatus productStatus;

    @Builder
    public ProductUploadDto(ProductStatus productStatus, String title, String text, int price, boolean locateAuthorization, String location, User uploadUser, String productImgUrl) {
        this.title = title;
        this.text = text;
        this.price = price;
        this.locateAuthorization = locateAuthorization;
        this.location = location;
        this.uploadUser = uploadUser;
        this.productImgUrl = productImgUrl;
        this.productStatus = productStatus;
    }

    public Product toEntity() {
        return Product.builder().title(title).price(price).text(text).productImgUrl(productImgUrl).user(uploadUser).category(category).locateAuthorization(true).productStatus(ProductStatus.RESERVE).build();
    }
}
