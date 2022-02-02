package com.example.market.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query(value = "select c.categoryId from Category as c where c.categoryId like ':categoryId%'")
    List<String> findChildId(String categoryId);


}
