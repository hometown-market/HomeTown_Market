package com.example.market.domain.wish;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    @Transactional
    public void wishing(long productId, String userId) {
        wishRepository.wishing(productId, userId);
    }

    @Transactional
    public void unwishing(long productId, String userId) {
        wishRepository.unwishing(productId, userId);
    }
}
