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

    private void createProduct(Category category) {
        for (int i = 0; i < 10; i++) {

            Product product = Product.builder()
                    .title(category.getCategoryName() + Integer.toString(i))
                    .price(i)
                    .text("prduct " + Integer.toString(i))
                    .productImgUrl("https://media.bunjang.co.kr/product/176911291_1_1642914208_w292.jpg")
                    .category(category)
                    .build();
            productRepository.save(product);
        }
    }

    private void initProduct() {
        Category category = new Category("10", "남성의류", null);
        categoryRepository.save(category);
        createProduct(category);
        category = new Category("1010", "코트,", "10");
        categoryRepository.save(category);
        createProduct(category);
        category = new Category("1020", "바지", "10");
        categoryRepository.save(category);
        createProduct(category);
        category = new Category("1030", "티셔츠", "10");
        categoryRepository.save(category);
        createProduct(category);
        category = new Category("1040", "맨투맨", "10");
        categoryRepository.save(category);
        createProduct(category);
        category = new Category("101010", "트렌치코트", "1010");
        categoryRepository.save(category);
        createProduct(category);
        category = new Category("101020", "겨울코트", "1010");
        categoryRepository.save(category);
        createProduct(category);


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
