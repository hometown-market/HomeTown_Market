package com.example.market.domain.product.dto;

import com.example.market.domain.product.Product;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ProductListDTO {
    private Long id;
    private boolean wish = false;
    private String title;
    private int price;
    private String productImgUrl;
    private LocalDateTime uploadDate;
    private long wishCount;



    public ProductListDTO(Product product) {
        this.id = product.getId();
        this.price = product.getPrice();
        this.productImgUrl = product.getProductImgUrl();
        this.title = product.getTitle();
        this.uploadDate = product.getUploadDate();
        this.wishCount = product.getWishCount();
    }
}
