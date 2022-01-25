package com.example.market.domain.product.controller;

import com.example.market.domain.product.Product;
import com.example.market.domain.product.dto.ProductDetailsDTO;
import com.example.market.domain.product.dto.ProductListDTO;
import com.example.market.domain.product.service.ProductService;
import com.example.market.domain.wish.WishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final WishService wishService;
    private final ProductService productService;

    @ApiOperation(value = "상품 찜", notes = "상품 찜")
    @PostMapping("/product/{productId}")
    public void wishing(@PathVariable long productId) {
        wishService.wishing(productId);
    }

    @ApiOperation(value = "상품 리스트 조회", notes = "상품 리스트 조회")
    @GetMapping("/api/product_list")
    public Page<ProductListDTO> productList(@PageableDefault(size = 20, sort = "uploadDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.productList(pageable);
    }


    @ApiOperation(value = "상품 검색", notes = "상품 검색")
    @GetMapping("/api/search/products")
    public Page<ProductListDTO> search(@PageableDefault(size = 20, sort = "uploadDate", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String keyword) {

        return productService.search(keyword, pageable);
    }

    @ApiOperation(value = "카테고리 상품 리스트 조회", notes = "카테고리 상품 조회")
    @GetMapping("/api/product_list/{categoryId}")
    public Page<Product> categoryProduct(@PageableDefault(size = 20, sort = "uploadDate", direction = Sort.Direction.DESC) Pageable pageable, @PathVariable long categoryId) {

        Page<Product> products = productService.categoryProduct(pageable, categoryId);

        return products;
    }

    @ApiOperation(value = "상품 조회", notes = "상품 조회")
    @GetMapping("/product/{productId}")
    public ProductDetailsDTO productDetail(@PathVariable long productId) {
        ProductDetailsDTO productDetailsDTO = productService.getProductDto(productId);
        return productDetailsDTO;
    }
}
