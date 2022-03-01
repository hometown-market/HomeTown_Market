package com.example.market.domain.wish;

import com.example.market.domain.product.Product;
import com.example.market.domain.product.repository.ProductRepository;
import com.example.market.domain.user.User;
import com.example.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    public boolean wishing(long productId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Product product = productRepository.getById(productId);
        User user = userRepository.findByEmail(email);

        Set<Long> productIds = wishRepository.getProductIds(user.getId());

        if (user != null && product != null) {
            Wish wish = new Wish(product, user);

            if (productIds.contains(productId)) {
                wishRepository.delete(wish);
                return false;
            } else {
                wishRepository.save(wish);
                return true;
            }


        }
        return false;
    }

}
