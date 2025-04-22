package org.example.bootjwt.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration-ms}")
    private long validityMs; // 만료가 언제되냐 ms (1/1000)초

    //토큰 생성
    public String createToken(Authentication auth) {
        String username = auth.getName();
        Instant now = Instant.now();
        Date expiryDate = new Date(now.toEpochMilli() + validityMs);
        SecretKey signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(expiryDate)
                .signWith(signingKey, Jwts.SIG.HS256)
                .compact();
    }


}
