package com.example.market.domain.product;

import com.example.market.domain.user.User;
import com.example.market.domain.user.repository.UserRepository;
import com.example.market.domain.wish.Wish;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    public Page<Product> search(String keyword, Pageable pageable) {
        return productRepository.findByTitleContainsOrderByUploadDateDesc(keyword, pageable);
    }

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

    public Page<Product> categoryProduct(Pageable pageable, long categoryId) {
        for (Product product : productRepository.findAll(pageable)) {
            if (product.getCategory().getCategoryId() == categoryId) return (Page<Product>) product;
        }
        return null;
    }


    public ProductDto getProductDto(long productId) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productId);

        Product product = productRepository.findById(productId).orElse(null);

        productDto.setText(product.getText());
        productDto.setTitle(product.getTitle());
        productDto.setUploadDate(product.getUploadDate());
        productDto.setWish(product.isWish());
        productDto.setWishCount(product.getWishList().size());

        return productDto;
    }

}
