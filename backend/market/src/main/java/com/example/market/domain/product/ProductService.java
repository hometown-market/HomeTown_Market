package com.example.market.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void searching(String keyword, Pageable pageable) {
        productRepository.search(keyword, pageable);
    }

    @Transactional
    public Page<Product> searchAll(String userId, Pageable pageable) {
        Page<Product> products = productRepository.productList(userId, pageable);

        products.forEach(product -> {
            product.setWishCount(product.getWishList().size());
            product.getWishList().forEach(wish -> {
                if (wish.getUser().getName() == userId) product.setWishState(true);
            });
        });
        return products;
    }
}
