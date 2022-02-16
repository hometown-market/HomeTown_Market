package com.example.market.global.security.util;

import com.example.market.domain.user.UserJwtToken;
import com.example.market.domain.user.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {


    public static final long JWT_TOKEN_VALIDITY = 60;
    public static final long JWT_REFRESH_VALIDITY = 60 * 60 * 24 * 7;
    private final TokenRepository tokenRepository;

    @Value("jwt.secret.access")
    private String secret;

    //retrieve username from jwt token
    public String getSubjectFromToken(String token) {

        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getKeyFromToken(String token, String key) {
        return (String) getAllClaimsFromToken(token).get(key);
    }

    public String getEmailFromToken(String token) {
        return getKeyFromToken(token, "email");
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    public String AccessGenerateToken(Map<String, Object> claims) {

        return Jwts.builder().setClaims(claims).setSubject((String) claims.get("email")).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String RefreshGenerateToken(Map<String, Object> claims) {

        return Jwts.builder().setClaims(claims).setSubject((String) claims.get("email")).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_REFRESH_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getEmailFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public UserJwtToken StoreRefreshToken(String email, String refreshToken) {
        UserJwtToken tokenHash = new UserJwtToken(email, refreshToken, this.getExpirationDateFromToken(refreshToken));
        return tokenRepository.save(tokenHash);
    }
}