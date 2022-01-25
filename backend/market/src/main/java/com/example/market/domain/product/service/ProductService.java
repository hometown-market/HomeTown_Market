package com.example.market.domain.product.service;


import com.example.market.domain.product.Product;
import com.example.market.domain.product.dto.ProductDetailsDTO;
import com.example.market.domain.product.dto.ProductListDTO;
import com.example.market.domain.product.repository.ProductRepository;
import com.example.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Page<ProductListDTO> search(String keyword, Pageable pageable) {
        Page<Product> products = productRepository.findByTitleContainsOrderByUploadDateDesc(keyword, pageable);
        return getProductsListDTO(products);
    }

    public Page<ProductListDTO> productList(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        //        for (Product product : products) {
//            product.setWishCount(product.getWishList().size());
//            product.getWishList().forEach(wish -> {
//                if (wish.getUser().getName().equals(userName)) product.setIsWish(true);
//            });
//        }
        return getProductsListDTO(products);
    }

    public Page<Product> categoryProduct(Pageable pageable, long categoryId) {
        for (Product product : productRepository.findAll(pageable)) {
            if (product.getCategory().getCategoryId() == categoryId) return (Page<Product>) product;
        }
        return null;
    }

    public ProductDetailsDTO getProductDto(long productId) {

        Product product = productRepository.findById(productId).orElse(null);
        ProductDetailsDTO productDetailsDTO = ProductDetailsDTO.builder()
                .id(product.getId())
                .text(product.getText())
                .title(product.getTitle())
                .uploadDate(product.getUploadDate())
                .uploadUser(product.getUser())
                .wishCount(product.getWishCount())
                .productImgUrl(product.getProductImgUrl())
                .price(product.getPrice())
                .build();


        return productDetailsDTO;
    }

    private Page<ProductListDTO> getProductsListDTO(Page<Product> products) {
        Page<ProductListDTO> map = products.map(product -> ProductListDTO.builder()
                .price(product.getPrice())
                .productImgUrl(product.getProductImgUrl())
                .title(product.getTitle())
                .uploadDate(product.getUploadDate())
                .wishCount(product.getWishCount()).build());
        return map;
    }
}
