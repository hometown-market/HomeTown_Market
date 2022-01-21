package com.example.market.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE product_title LIKE '%keyword%' ORDER BY upload_date DESC", nativeQuery = true)
    Page<Product> search(String keyword, Pageable pageable, long resultCount);

    @Query(value = "SELECT * FROM product ORDER BY upload_date DESC", nativeQuery = true)
    Page<Product> productList(Pageable pageable);

    @Query(value = "SELECT * FROM product WHERE category_id = :categoryId ORDER BY upload_date DESC", nativeQuery = true)
    Page<Product> categoryProduct(long categoryId, Pageable pageable, long resultCount);

    @Query(value = "SELECT count(*) FROM product WHERE category_id = :categoryId", nativeQuery = true)
    Long categoryProductCount(long categoryId);

    @Query(value = "SELECT count(*) FROM product WHERE product_title LIKE '%keyword%'", nativeQuery = true)
    Long searchCount(String keyword);
}
