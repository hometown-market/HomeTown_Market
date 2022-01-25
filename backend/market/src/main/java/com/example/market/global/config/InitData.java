package com.example.market.global.config;

import com.example.market.domain.product.Product;
import com.example.market.domain.product.repository.ProductRepository;
import com.example.market.domain.user.User;
import com.example.market.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class InitData {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public InitData(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        initProduct();
        initUser();
    }

    private void initProduct() {
        for (int i = 0; i < 20; i++) {

            Product product = Product.builder()
                    .title("prduct " + Integer.toString(i))
                    .price(i)
                    .text("prduct " + Integer.toString(i))
                    .productImgUrl("https://media.bunjang.co.kr/product/176911291_1_1642914208_w292.jpg")
                    .build();
            productRepository.save(product);
        }


    }

    private void initUser() {
        for (int i = 0; i < 20; i++) {

            User user = User.builder()
                    .email(Integer.toString(i))
                    .phone("01032222222")
                    .password("1")
                    .build();
            userRepository.save(user);
        }

    }


}
