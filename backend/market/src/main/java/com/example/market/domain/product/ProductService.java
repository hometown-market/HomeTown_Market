package com.example.market.domain.product;

import com.example.market.domain.user.User;
import com.example.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public Page<Product> searching(String userId, String keyword) {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "name"));
        long resultCount = productRepository.searchCount(keyword);
        Page<Product> searchProduct = productRepository.search(keyword, pageRequest, resultCount);

        searchProduct.forEach(product -> {
            product.setWishCount(product.getWishList().size());
            product.getWishList().forEach(wish -> {
                if (wish.getUser().getName() == userId) product.setWishState(1);
            });
        });
        return searchProduct;
    }

    @Transactional
    public Page<Product> searchAll(String userId) {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "name"));

        Page<Product> products = productRepository.productList(pageRequest);

        products.forEach(product -> {
            product.setWishCount(product.getWishList().size());
            product.getWishList().forEach(wish -> {
                if (wish.getUser().getName() == userId) product.setWishState(1);
            });
        });
        return products;
    }

    @Transactional
    public Page<Product> categoryProductList(String userId, long categoryId) {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "name"));
        long resultCount = productRepository.categoryProductCount(categoryId);
        Page<Product> categoryProducts = productRepository.categoryProduct(categoryId, pageRequest, resultCount);

        categoryProducts.forEach(product -> {
            product.setWishCount(product.getWishList().size());
            product.getWishList().forEach(wish -> {
                if (wish.getUser().getName() == userId) product.setWishState(1);

            });
        });
        return categoryProducts;
    }

    @Transactional
    public ProductDto getProductDto(long productId, String userid) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productId);

        Product product = productRepository.findById(productId).get();

        productDto.setText(product.getText());
        productDto.setTitle(product.getTitle());
        productDto.setUploadDate(product.getUploadDate());
        productDto.setWishState(product.getWishState());
        productDto.setWishCount(product.getWishList().size());
        product.getWishList().forEach(wish -> {
            if(wish.getUser().getName() == userid) productDto.setWishState(1);
        });

        User user = userRepository.findById(product.getUser().getId()).get();
        productDto.setUploadUser(user);
        return productDto;
    }
}
