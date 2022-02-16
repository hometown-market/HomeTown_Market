package com.example.market.domain.user.repository;

import com.example.market.domain.user.UserJwtToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<UserJwtToken,String> {

}
