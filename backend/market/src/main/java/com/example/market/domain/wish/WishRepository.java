package com.example.market.domain.wish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    Wish findByProduct_IdAndUser_Id(long product_id, long user_id);
}