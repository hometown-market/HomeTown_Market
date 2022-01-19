package com.example.market.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Page<Product> searching(String userId, String keyword, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "name"));
        Page<Product> searchProduct = productRepository.search(keyword, pageRequest);

        searchProduct.forEach(product -> {
            product.setWishCount(product.getWishList().size());
            product.getWishList().forEach(wish -> {
                if (wish.getUser().getName() == userId) product.setWishState(1);
            });
        });
        return searchProduct;
    }

    @Transactional
    public Page<Product> searchAll(String userId, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "name"));

        Page<Product> products = productRepository.productList(pageRequest);

        products.forEach(product -> {
            product.setWishCount(product.getWishList().size());
            product.getWishList().forEach(wish -> {
                if (wish.getUser().getName() == userId) product.setWishState(1);
            });
        });
        return products;
    }

    @Transactional
    public Page<Product> categoryProductList(String userId, long categoryId, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "name"));
        Page<Product> categoryProducts = productRepository.categoryProduct(categoryId, pageRequest);

        categoryProducts.forEach(product -> {
            product.setWishCount(product.getWishList().size());
            product.getWishList().forEach(wish -> {
                if (wish.getUser().getName() == userId) product.setWishState(1);

            });
        });
        return categoryProducts;
    }
}
