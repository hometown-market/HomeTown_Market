package com.example.market.domain.product;

import com.example.market.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private long id;
    private String text;
    private String title;
    private LocalDateTime uploadDate;
    private User uploadUser;
    private long wishCount;
    private boolean isWish;
    private long views; //조회수
    private String productImgUrl;
    private long price;

}
