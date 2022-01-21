package com.example.market.domain.wish;

import com.example.market.domain.product.Product;
import com.example.market.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "wish", uniqueConstraints = {
        @UniqueConstraint(name = "wishing", columnNames = {"product_id", "user_id"})
})
@RequiredArgsConstructor
@Entity
public class Wish {

    @Id
    @GeneratedValue
    @Column(name = "wish_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Wish(Product product, User user) {
        this.product = product;
        this.user = user;
    }
}
