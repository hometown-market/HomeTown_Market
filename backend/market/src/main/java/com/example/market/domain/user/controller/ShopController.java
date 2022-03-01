package com.example.market.domain.user.controller;

import com.example.market.domain.product.dto.ProductListDTO;
import com.example.market.domain.user.service.ShopService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;


    @ApiOperation(value = "내 상점 찜한것", notes = "상점 찜한것")
    @GetMapping(value = "/shop/wish")
    public Page<ProductListDTO> WishList() {
        return shopService.ShopWishList();
    }

    @ApiOperation(value = "내 상점 상품 목록")
    @GetMapping(value = "/shop/products")
    public Page<ProductListDTO> ShopProducts() {

        return shopService.ShopProducts();

    }

    @ApiOperation("정보 변경")
    @PostMapping("/api/shop/change")
    public String changeName(@RequestBody String newName, @RequestPart MultipartFile newPicture) {
        shopService.change(newName, newPicture);
        return newName;
    }
}
