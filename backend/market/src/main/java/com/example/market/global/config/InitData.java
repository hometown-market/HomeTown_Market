package com.example.market.global.config;

import com.example.market.domain.category.Category;
import com.example.market.domain.category.CategoryRepository;
import com.example.market.domain.product.Product;
import com.example.market.domain.product.repository.ProductRepository;
import com.example.market.domain.user.User;
import com.example.market.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class InitData {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public InitData(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        initProduct();
        initUser();
    }

    private void initProduct() {

        Category category1 = new Category(10L, "test1", 0L);
        Category category2 = new Category(11L, "test2", 0L);
        Category category3 = new Category(1010L, "test1-1", 10L);
        Category category4 = new Category(1011L, "test1-2", 10L);
        Category category5 = new Category(101010L, "test1-1-1", 1010L);
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);
        for (int i = 0; i < 20; i++) {

            Product product = Product.builder()
                    .title("prduct " + Integer.toString(i))
                    .price(i)
                    .text("prduct " + Integer.toString(i))
                    .productImgUrl("https://media.bunjang.co.kr/product/176911291_1_1642914208_w292.jpg")
                    .category(categoryRepository.getById(101010L))
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
