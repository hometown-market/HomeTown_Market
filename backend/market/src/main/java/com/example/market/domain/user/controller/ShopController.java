package com.example.market.domain.user.controller;

import com.example.market.domain.product.dto.ProductListDTO;
import com.example.market.domain.user.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;


    @GetMapping(value = "/wish")
    public Page<ProductListDTO> WishList() {
        return shopService.ShopWishList();
    }

    @GetMapping(value = "/shop/products")
    public Page<ProductListDTO> ShopProducs() {

        return shopService.ShopProducts();

    }
}
