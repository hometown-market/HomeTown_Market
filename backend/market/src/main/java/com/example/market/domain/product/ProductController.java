package com.example.market.domain.product;

import com.example.market.domain.wish.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final WishService wishService;
    private final ProductService productService;

    @PostMapping("/product/{productId}")
    public void wishing(@PathVariable long productId, Authentication authentication) {
        try {
            wishService.wishing(productId, authentication.getName());
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    @GetMapping("/api/product_list")
    public void productList(Authentication authentication, Pageable pageable) {
        productService.searchAll(authentication.getName(), pageable);
    }

    @GetMapping("/api/product_list")
    public void search(Authentication authentication, @RequestParam String keyword, Pageable pageable) {
        productService.searching(authentication.getName(), keyword, pageable);
    }

    @GetMapping("/api/product_list/{categoryId}")
    public void categoryProduct(Authentication authentication, @PathVariable long categoryId, Pageable pageable) {
        productService.categoryProductList(authentication.getName(), categoryId, pageable);
    }
}
