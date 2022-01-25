package com.example.market.domain.product.repository;

import com.example.market.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE LIKE '%keyword%' ORDER BY DESC", nativeQuery = true)
    Page<Product> findByTitle(String keyword, Pageable pageable);

    Page<Product> findByTitleContainsOrderByUploadDateDesc(String title, Pageable pageable);


}
