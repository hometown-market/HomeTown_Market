package com.example.market.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Modifying
//    @Query(value = "SELECT * FROM product WHERE product_title LIKE '%keyword%' ORDER BY upload_date DESC", nativeQuery = true)
//    Page<Product> search(String userId, String keyword, Pageable pageable);
//
//    @Modifying
//    @Query(value = "SELECT * FROM product ORDER BY upload_date DESC", nativeQuery = true)
//    Page<Product> productList(String userId, Pageable pageable);
//
//    @Modifying
//    @Query(value = "SELECT * FROM product WHERE category_id = :categoryId ORDER BY upload_date DESC", nativeQuery = true)
//    Page<Product> categoryProduct(long categoryId, String userId, Pageable pageable);
}
