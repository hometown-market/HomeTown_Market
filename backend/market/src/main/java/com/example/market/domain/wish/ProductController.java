package com.example.market.domain.wish;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final WishService wishService;

    @PostMapping("/product/{productId}/wishing")
    public void wishing(@PathVariable long productId, Authentication authentication) {
        wishService.wishing(productId, authentication.getName());
    }

    @DeleteMapping("/product/{productId}/unwishing")
    public void unwishing(@PathVariable long productId, Authentication authentication) {
        wishService.unwishing(productId, authentication.getName());
    }
}
