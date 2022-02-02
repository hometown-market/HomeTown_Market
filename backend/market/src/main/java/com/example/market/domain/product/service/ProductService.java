package com.example.market.domain.product.service;


import com.example.market.domain.category.CategoryDto;
import com.example.market.domain.category.CategoryRepository;
import com.example.market.domain.product.dto.ProductCategoryCountDto;
import com.example.market.domain.product.dto.ProductDetailsDTO;
import com.example.market.domain.product.dto.ProductListDTO;
import com.example.market.domain.product.repository.ProductRepository;
import com.example.market.domain.user.repository.UserRepository;
import com.example.market.domain.wish.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final WishRepository wishRepository;
    private final ProductServiceUtil serviceUtil;
    private final CategoryRepository categoryRepository;

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


    public List<Object> categoryProduct(Pageable pageable, String categoryId) {
        Page<ProductListDTO> products = productRepository.findByCategory_CategoryIdStartsWithOrderByUploadDateDesc(categoryId, pageable).map(ProductListDTO::new);
        List<ProductCategoryCountDto> productCategoryCountDtos = productRepository.CountByCategoryId(categoryId);
        List<String> categoryDtoList = categoryRepository.findChildId(categoryId);
        List<Object> pageWithCounts = new ArrayList<>();
        pageWithCounts.add(products);
        pageWithCounts.add(productCategoryCountDtos);
        pageWithCounts.add(categoryDtoList);
        return pageWithCounts;
    }

    public Optional<ProductDetailsDTO> productDetail(long productId) {
        Optional<ProductDetailsDTO> productDetailsDTO = productRepository.findById(productId).map(ProductDetailsDTO::new);

        return productDetailsDTO;
    }


}
