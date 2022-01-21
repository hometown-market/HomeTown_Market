package com.example.market.domain.product;

import com.example.market.domain.wish.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final WishService wishService;
    private final ProductService productService;

    @PostMapping("/product/{productId}")
    public void wishing(@PathVariable long productId, Authentication authentication) {
        wishService.wishing(authentication.getName(), productId);
    }

    @GetMapping("/api/product_list")
    public Page<Product> productList(@PageableDefault(size = 20, sort = "uploadDate", direction = Sort.Direction.DESC) Pageable pageable, Authentication authentication) {
        return productService.productList(pageable, authentication.getName());
    }

    @GetMapping("/api/product_list/{keyword}")
    public Page<Product> search(@PageableDefault(size = 20, sort = "uploadDate", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String keyword) {
        return productService.search(keyword, pageable);
    }

    @GetMapping("/api/product_list/{categoryId}")
    public Page<Product> categoryProduct(@PageableDefault(size = 20, sort = "uploadDate", direction = Sort.Direction.DESC) Pageable pageable, @PathVariable long categoryId) {
        return productService.categoryProduct(pageable, categoryId);
    }

    @GetMapping("/product/{productId}")
    public void productDetail(Authentication authentication, @PathVariable long productId) {
        productService.getProductDto(productId, authentication.getName());
    }
}
