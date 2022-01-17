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

    @GetMapping("api/v1/productList")
    public void productList(Authentication authentication, Pageable pageable) {
        productService.searchAll(authentication.getName(), pageable);
    }

    @GetMapping("/search")
    public void search(@RequestParam String productName, Pageable pageable) {
        productService.searching(productName, pageable);
    }
}
