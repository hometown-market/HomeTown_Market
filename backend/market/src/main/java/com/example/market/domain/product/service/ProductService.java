package com.example.market.domain.product.service;


import com.example.market.domain.product.Product;
import com.example.market.domain.product.dto.ProductDetailsDTO;
import com.example.market.domain.product.dto.ProductListDTO;
import com.example.market.domain.product.repository.ProductRepository;
import com.example.market.domain.user.repository.UserRepository;
import com.example.market.domain.wish.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final WishRepository wishRepository;
    private final ProductServiceUtil serviceUtil;
    public static final String ROLE_USER = "ROLE_USER";

    public Page<ProductListDTO> search(String keyword, Pageable pageable) {
        Page<ProductListDTO> products = productRepository.findByTitleContainsOrderByUploadDateDesc(keyword, pageable).map(ProductListDTO::new);
        serviceUtil.ProductListDTOSetWish(products);

        return products;
    }

    public Page<ProductListDTO> productList(Pageable pageable) {


        Page<ProductListDTO> products = productRepository.findAll(pageable).map(ProductListDTO::new);
        serviceUtil.ProductListDTOSetWish(products);

        return products;
    }

    public Page<Product> categoryProduct(Pageable pageable, long categoryId) {
//        ProductServiceUtil.getAuthentication();

        for (Product product : productRepository.findAll(pageable)) {
            if (product.getCategory().getCategoryId() == categoryId) return (Page<Product>) product;
        }
        return null;
    }

    public Optional<ProductDetailsDTO> productDetail(long productId) {
        Optional<ProductDetailsDTO> productDetailsDTO = productRepository.findById(productId).map(ProductDetailsDTO::new);

        return productDetailsDTO;
    }

}
