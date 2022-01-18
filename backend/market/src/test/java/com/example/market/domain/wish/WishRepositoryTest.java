package com.example.market.domain.wish;

import com.example.market.domain.product.Product;
import com.example.market.domain.product.ProductRepository;
import com.example.market.domain.user.User;
import com.example.market.domain.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WishRepositoryTest {

    @Autowired
    WishRepository wishRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    private User user;
    private Product product;

    @BeforeEach
    public void setUp() {
        user = User.builder().address("서울").name("test").password("12345d").build();
        product = Product.builder().productImgUrl("test.jpg").text("good").price(10000).name("test").user(user).wishState(true).build();
        userRepository.save(user);
        productRepository.save(product);
    }

    @AfterEach
    public void tearDown() {
        wishRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void deleteWishByProduct() {
        Wish wish = Wish.builder().user(user).product(product).build();
        wishRepository.save(wish);

        wishRepository.deleteWish(product.getId(), user.getName());

        Assertions.assertThat(wishRepository.findAll().size()).isEqualTo(0);
    }

}