package com.example.market.domain.user;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@Getter
@RedisHash(value = "refresh", timeToLive = 60 * 60 * 24 * 7)
public class UserJwtToken {

    @Id
    String tokenId;
    String refreshToken;
    Date date;


    public UserJwtToken(String tokenId, String refreshToken, Date date) {
        this.tokenId = tokenId;
        this.refreshToken = refreshToken;
        this.date = date;
    }
}
