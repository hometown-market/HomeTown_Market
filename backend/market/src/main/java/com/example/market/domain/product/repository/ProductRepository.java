package com.example.market.domain.product.repository;

import com.example.market.domain.category.Category;
import com.example.market.domain.product.Product;
import com.example.market.domain.product.dto.ProductCategoryCountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.HashSet;


@Repository
public interface
ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.category.categoryId like concat(?1, '%') order by p.uploadDate DESC")
    Page<Product> findByCategory_CategoryIdStartsWithOrderByUploadDateDesc(String categoryId, Pageable pageable);

    @Query(value = "SELECT * FROM product WHERE LIKE '%keyword%' ORDER BY DESC", nativeQuery = true)
    Page<Product> findByTitle(String keyword, Pageable pageable);

    Page<Product> findByTitleContainsOrderByUploadDateDesc(String title, Pageable pageable);

    Page<Product> findByIdIsIn(Collection<Long> ids, Pageable pageable);

    @Query(value = "select p from Product as p join p.wishList w on w.user.id = :userId")
    Page<Product> findShopWishProducts(@Param("userId") long userId, Pageable pageable);

    Page<Product> findByUser_Id(long id, Pageable pageable);

    Page<Product> findAllByCategoryOrderByUploadDateDesc(Category category, Pageable pageable);

    @Query("select p from Product p where p.category.categoryId in ?1 order by p.uploadDate DESC")
    Page<Product> findByCategory_CategoryIdIsInOrderByUploadDateDesc(Collection<Long> categoryIds, Pageable pageable);

    @Query("select new com.example.market.domain.product.dto.ProductCategoryCountDto(p.category.categoryId,count(p) ) from Product p where p.category.categoryId like concat(?1, '%') group by p.category.categoryId")
    List<ProductCategoryCountDto> CountByCategoryId(String categoryId);


}
