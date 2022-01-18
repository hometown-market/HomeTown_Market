package com.example.market.domain.wish;

import com.example.market.domain.product.Product;
import com.example.market.domain.product.ProductRepository;
import com.example.market.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    @Transactional
    public void wishing(long productId, String userId) {

        // 리포지토리에서 조회
        if(wishRepository.findWish(productId, userId) == null) {
            wishRepository.wishing(productId, userId);

        } else {
            wishRepository.deleteWish(productId, userId);
        }
        // 널이면 찜 생성
        // 있으면 삭제하고 카운트 줄이고
    }
}
