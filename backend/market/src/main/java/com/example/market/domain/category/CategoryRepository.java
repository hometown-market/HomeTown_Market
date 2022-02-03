package com.example.market.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query(value = "select new com.example.market.domain.category.CategoryDto(c.categoryId, c.categoryName, c.parentId) from Category as c where c.categoryId like concat(?1, '__')")
    List<CategoryDto> findCategory(String categoryId);


}
