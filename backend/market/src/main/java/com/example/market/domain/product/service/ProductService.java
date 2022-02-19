package com.example.market.domain.product.service;


import com.example.market.domain.category.Category;
import com.example.market.domain.category.CategoryDto;
import com.example.market.domain.category.CategoryRepository;
import com.example.market.domain.product.Product;
import com.example.market.domain.product.dto.ProductCategoryCountDto;
import com.example.market.domain.product.dto.ProductDetailsDTO;
import com.example.market.domain.product.dto.ProductListDTO;
import com.example.market.domain.product.dto.ProductUploadDto;
import com.example.market.domain.product.repository.ProductRepository;
import com.example.market.domain.user.User;
import com.example.market.domain.user.repository.UserRepository;
import com.example.market.domain.wish.WishRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.swing.text.html.parser.Entity;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final WishRepository wishRepository;
    private final ProductServiceUtil serviceUtil;
    private final CategoryRepository categoryRepository;

    private String uploadImageUrl = "";

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
        List<CategoryDto> categoryDtoList = categoryRepository.findCategory(categoryId);
        Category category = categoryRepository.getById(categoryId);
        CategoryDto categoryDto = new CategoryDto(category.getCategoryId(), category.getCategoryName(), category.getParentId());
        List<Object> pageWithCounts = new ArrayList<>();
        pageWithCounts.add(products);
        pageWithCounts.add(productCategoryCountDtos);
        pageWithCounts.add(categoryDto);
        pageWithCounts.add(categoryDtoList);
        return pageWithCounts;
    }

    public Optional<ProductDetailsDTO> productDetail(long productId) {
        Optional<ProductDetailsDTO> productDetailsDTO = productRepository.findById(productId).map(ProductDetailsDTO::new);

        return productDetailsDTO;
    }

    public void saveImage(ProductUploadDto productUploadDto, MultipartFile multipartFile) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new IllegalStateException();
        }

        UUID uuid = UUID.randomUUID();
        String imgFileName = uuid + "_" + multipartFile.getOriginalFilename();
        Path imageFilePath = Paths.get(uploadImageUrl + imgFileName);
        try {
            Files.write(imageFilePath, multipartFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        productUploadDto.setProductImgUrl(imgFileName);
        productUploadDto.setUploadUser(user);
        saveProduct(productUploadDto);
    }
    public Long saveProduct(ProductUploadDto productUploadDto) {
        return productRepository.save(productUploadDto.toEntity()).getId();
    }

}
