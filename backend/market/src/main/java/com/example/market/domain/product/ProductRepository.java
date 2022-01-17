package com.example.market.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query(value = "SELECT * FROM product WHERE product_name LIKE '%keyword%' ORDER BY uploadDate DESC", nativeQuery = true)
    Page<Product> search(String keyword, Pageable pageable);

    @Modifying
    @Query(value = "SELECT * FROM product ORDER BY uploadDate DESC", nativeQuery = true)
    Page<Product> productList(String userId, Pageable pageable);

}
