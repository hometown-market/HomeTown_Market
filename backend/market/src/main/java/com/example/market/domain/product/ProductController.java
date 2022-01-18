//package com.example.market.domain.product;
//
//import com.example.market.domain.wish.WishService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//public class ProductController {
//
//    private final WishService wishService;
//
//    @PostMapping("/product/{productId}/wishing")
//    public void wishing(@PathVariable long productId, Authentication authentication) {
//        wishService.wishing(productId, authentication.getName());
//    }
//
//    @GetMapping("api/v1/productList")
//    public List<Product> productList() {
//        return null;
//    }
//
//    //select * from Product where %keyword%
//}
