package com.example.market.domain.wish;

import com.example.market.domain.product.Product;
import com.example.market.domain.product.repository.ProductRepository;
import com.example.market.domain.user.User;
import com.example.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    public void wishing(long productId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Product product = productRepository.getById(productId);
        User user = userRepository.findByEmail(email);

        if (user != null && product != null) {
            Wish wish = wishRepository.findByProduct_IdAndUser_Id(productId, user.getId());
            if (wish != null) {
                wishRepository.delete(wish);
            } else {
                wishRepository.save(new Wish(product, user));
            }
        }
    }
}
