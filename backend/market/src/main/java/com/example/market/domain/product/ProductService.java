package com.example.market.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProductService {
//
//    private final ProductRepository productRepository;
//
//    @Transactional
//    public Page<Product> searching(String userId, String keyword, Pageable pageable) {
//        Page<Product> searchProduct = productRepository.search(userId, keyword, pageable);
//
//        searchProduct.forEach(product -> {
//            product.setWishCount(product.getWishList().size());
//            product.getWishList().forEach(wish -> {
//                if (wish.getUser().getName() == userId) product.setWishState(1);
//            });
//        });
//        return searchProduct;
//    }
//
//    @Transactional
//    public Page<Product> searchAll(String userId, Pageable pageable) {
//        Page<Product> products = productRepository.productList(userId, pageable);
//
//        products.forEach(product -> {
//            product.setWishCount(product.getWishList().size());
//            product.getWishList().forEach(wish -> {
//                if (wish.getUser().getName() == userId) product.setWishState(1);
//            });
//        });
//        return products;
//    }
//
//    @Transactional
//    public Page<Product> categoryProductList(String userId, long categoryId, Pageable pageable) {
//        Page<Product> categoryProducts = productRepository.categoryProduct(categoryId, userId, pageable);
//
//        categoryProducts.forEach(product -> {
//            product.setWishCount(product.getWishList().size());
//            product.getWishList().forEach(wish -> {
//                if (wish.getUser().getName() == userId) product.setWishState(1);
//
//            });
//        });
//        return categoryProducts;
//    }
}
