package com.example.market.domain.product.repository;

import com.example.market.domain.category.Category;
import com.example.market.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface
ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE LIKE '%keyword%' ORDER BY DESC", nativeQuery = true)
    Page<Product> findByTitle(String keyword, Pageable pageable);

    Page<Product> findByTitleContainsOrderByUploadDateDesc(String title, Pageable pageable);

    Page<Product> findByIdIsIn(Collection<Long> ids, Pageable pageable);

    @Query(value = "select p from Product as p join p.wishList w on w.user.id = :userId")
    Page<Product> findShopWishProducts(@Param("userId") long userId, Pageable pageable);

    Page<Product> findByUser_Id(long id, Pageable pageable);

    Page<Product> findAllByCategoryOrderByUploadDateDesc(Category category, Pageable pageable);
}
