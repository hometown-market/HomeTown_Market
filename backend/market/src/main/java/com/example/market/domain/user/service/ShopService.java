package com.example.market.domain.user.service;

import com.example.market.domain.product.Product;
import com.example.market.domain.product.dto.ProductListDTO;
import com.example.market.domain.product.repository.ProductRepository;
import com.example.market.domain.product.service.ProductServiceUtil;
import com.example.market.domain.user.User;
import com.example.market.domain.user.repository.UserRepository;
import com.example.market.domain.wish.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final UserRepository userRepository;
    private final WishRepository wishRepository;
    private final ProductRepository productRepository;
    private final ProductServiceUtil serviceUtil;

    public Page<ProductListDTO> ShopWishList() {

        long userId = ProductServiceUtil.getAuthentication();
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "uploadDate"));
        Page<Product> shopWishProducts = productRepository.findShopWishProducts(userId, pageRequest);

        serviceUtil.ProductListDTOSetWish(shopWishProducts.map(ProductListDTO::new));
        return shopWishProducts.map(ProductListDTO::new);
    }

    public Page<ProductListDTO> ShopProducts() {
        long userId = ProductServiceUtil.getAuthentication();
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "uploadDate"));
        Page<Product> page = productRepository.findByUser_IdAndProductStatus(userId, null,pageRequest);
        Page<ProductListDTO> products = page.map(ProductListDTO::new);

        serviceUtil.ProductListDTOSetWish(products);
        return products;
    }

    public void change(String newName, MultipartFile newPicture) {
        long userId = ProductServiceUtil.getAuthentication();

        User user = userRepository.getById(userId);
    }
}
