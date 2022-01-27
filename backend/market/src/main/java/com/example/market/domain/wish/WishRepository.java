package com.example.market.domain.wish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {


    @Query("select w.product.id from Wish w where w.user.id = ?1")
    Set<Long> getProductIds(long id);


}