package com.example.market.domain.product.service;

import com.example.market.domain.product.dto.ProductListDTO;
import com.example.market.domain.user.User;
import com.example.market.domain.wish.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProductServiceUtil {

    private final WishRepository wishRepository;
    public static long getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();

        return principal.getId();
    }

    public void ProductListDTOSetWish(Page<ProductListDTO> products) {
        long userid = getAuthentication();
        Set<Long> productIds = wishRepository.getProductIds(userid);
        products.iterator().forEachRemaining(productListDTO -> {
            if (productIds.contains(productListDTO.getId())) {
                productListDTO.setWish(true);
            }
        });
    }
}
