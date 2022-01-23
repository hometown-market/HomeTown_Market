package com.example.market.global.config;

import com.example.market.domain.product.Product;
import com.example.market.domain.product.ProductRepository;
import com.example.market.domain.user.User;
import com.example.market.domain.user.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component


public class InitData {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public InitData(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        initProduct();
    }

    private void initProduct() {
        for (int i = 0; i < 20; i++) {

            Product product = Product.builder()
                    .title("prduct " + Integer.toString(i))
                    .price(i)
                    .text("prduct " + Integer.toString(i)).build();
            productRepository.save(product);
        }


    }

    private void initUser() {
        for (int i = 0; i < 20; i++) {

            User user = User.builder()
                    .email(Integer.toString(i) + "@gmail.com")
                    .phone("01032222222")
                    .build();
            userRepository.save(user);
        }

    }


}
