package com.example.market.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductListDTO {
    private String title;
    private int price;
    private String productImgUrl;
    private LocalDateTime uploadDate;
    private long wishCount;



}
