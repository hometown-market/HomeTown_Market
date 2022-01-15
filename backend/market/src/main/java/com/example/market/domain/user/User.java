package com.example.market.domain.user;

import com.example.market.domain.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String email;
    private String password;
    private String name;
    private String profileImgUrl;

    @OneToMany(mappedBy = "user_id", fetch = FetchType.LAZY)
    private List<Product> productList;
}
