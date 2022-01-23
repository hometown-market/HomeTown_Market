package com.example.market.domain.product;

import com.example.market.domain.user.User;
import com.example.market.domain.user.repository.UserRepository;
import com.example.market.domain.wish.Wish;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Transactional
    public Page<Product> search(String keyword, Pageable pageable) {
        return productRepository.findByTitle(keyword, pageable);
    }

    @Transactional
    public Page<Product> productList(Pageable pageable, String userName) {
        Page<Product> products = productRepository.findAll(pageable);
        for (Product product : products) {
            product.setWishCount(product.getWishList().size());
            product.getWishList().forEach(wish -> {
                if (wish.getUser().getName().equals(userName)) product.setIsWish(true);
            });
        }
        return products;
    }

    @Transactional
    public long productCount() {
        return productRepository.findAll().size();
    }

    @Transactional
    public long categoryProductCount(long categoryId) {
        List<Product> categoryProduct = new ArrayList<>();
        for(Product product : productRepository.findAll()) {
            if(product.getCategory().getCategoryId() == categoryId) categoryProduct.add(product);
        }
        return categoryProduct.size();
    }

    @Transactional
    public Page<Product> categoryProduct(Pageable pageable, long categoryId) {
        for (Product product : productRepository.findAll(pageable)) {
            if (product.getCategory().getCategoryId() == categoryId) return (Page<Product>) product;
        }
        return null;
    }


    @Transactional
    public void getProductDto(long productId, String userName) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productId);

        Product product = productRepository.findById(productId).orElse(null);

        try {
            productDto.setText(product.getText());
            productDto.setTitle(product.getTitle());
            productDto.setUploadDate(product.getUploadDate());
            productDto.setWish(product.isWish());
            productDto.setWishCount(product.getWishList().size());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        for (Wish wish : product.getWishList()) {
            if (wish.getUser().getName().equals(userName)) productDto.setWish(true);
        }

        User user = userRepository.findById(product.getUser().getId()).orElse(null);
        productDto.setUploadUser(user);
    }

}
