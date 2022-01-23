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
    public Page<Product> productList(@PageableDefault(size = 20, sort = "uploadDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.productList(pageable);
    }

    @GetMapping("/api/search/products")
    public Page<Product> search(@PageableDefault(size = 20, sort = "uploadDate", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String keyword) {
        return productService.search(keyword, pageable);
    }

    @GetMapping("/api/product_list/{categoryId}")
    public Page<Product> categoryProduct(@PageableDefault(size = 20, sort = "uploadDate", direction = Sort.Direction.DESC) Pageable pageable, @PathVariable long categoryId) {
        return productService.categoryProduct(pageable, categoryId);
    }

    @GetMapping("/product/{productId}")
    public ProductDto productDetail(Authentication authentication, @PathVariable long productId) {
        ProductDto productDto = productService.getProductDto(productId);
        return productDto;
    }
}
