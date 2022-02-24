package com.example.market.domain.product.controller;

import com.example.market.domain.product.dto.ProductDetailsDTO;
import com.example.market.domain.product.dto.ProductListDTO;
import com.example.market.domain.product.dto.ProductUploadDto;
import com.example.market.domain.product.service.ProductService;
import com.example.market.domain.wish.WishService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final WishService wishService;
    private final ProductService productService;

    @ApiOperation(value = "상품 찜", notes = "상품 찜")
    @PostMapping("/api/products/wish/{productId}")
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
    public List<Object> categoryProduct(@PageableDefault(size = 20, sort = "uploadDate", direction = Sort.Direction.DESC) Pageable pageable, @PathVariable String categoryId) {

        return productService.categoryProduct(pageable, categoryId);
    }

    @ApiOperation(value = "상품 조회", notes = "상품 조회")
    @GetMapping("/product/{productId}")
    public Optional<ProductDetailsDTO> productDetail(@PathVariable long productId) {
        return productService.productDetail(productId);
    }

    @ApiOperation(value = "상품 등룍")
    @PostMapping("/products/new")
    public String newProducts(ProductUploadDto productUploadDto, @RequestParam("productImgUrl") MultipartFile multipartFile, RedirectAttributes redirectAttributes, Authentication authentication) throws IOException, IllegalStateException {
        productService.saveImage(productUploadDto, multipartFile);
        redirectAttributes.addAttribute("user", authentication.getName());
        return "redirect:/";
    }
}
