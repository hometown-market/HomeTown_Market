package com.example.market.domain.wish;

import com.example.market.domain.product.Product;
import com.example.market.domain.user.User;
import com.example.market.domain.wish.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

//    @Modifying
//    @Query(value = "INSERT INTO wish(product_id, user_id) VALUES(:productId, :userId)", nativeQuery = true)
//    Wish wishing(long productId, String userId);
//
//    //추가할거 삭제, 조회
//    @Modifying
//    @Query(value = "SELECT * FROM wish WHERE post_id = :postId AND user_id = :userId", nativeQuery = true)
//    Wish findWish(long productId, String userId);
//
//    @Modifying
//    @Query(value = "DELETE FROM wish WHERE post_id = :postId AND user_id = :userId", nativeQuery = true)
//    Wish deleteWish(long productId, String userId);
}
