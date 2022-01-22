package com.example.market.domain.wish;

import com.example.market.domain.product.Product;
import com.example.market.domain.product.ProductRepository;
import com.example.market.domain.user.User;
import com.example.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public void wishing(String userName, long productId) {
        Product product = productRepository.getById(productId);
        User user = userRepository.findByName(userName);
        for(Wish wish : wishRepository.findAll()) {
            if(wish.getProduct().getId() == productId && wish.getUser().getName().equals(userName)) wishRepository.delete(wish);
        }
        wishRepository.findAll().add(new Wish(product, user));
    }
}
