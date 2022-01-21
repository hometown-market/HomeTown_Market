package com.example.market.domain.product;

import com.example.market.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductDto {

    private long id;
    private String text;
    private String title;
    private LocalDateTime uploadDate;
    private User uploadUser;
    private long wishCount;
    private boolean isWish;

}
