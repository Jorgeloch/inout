package com.inout.infrastructure.token;

import com.inout.domain.model.entities.Auth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JWTTokenService {
    private final SecretKey key;
    private final Duration expiration;

    public JWTTokenService(
            @Value("${security.jwt.secret}") String key,
            @Value("${security.jwt.expliration}") long expiration
    ) {
        this.key = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        this.expiration = Duration.ofMillis(expiration);
    }

    public String generateToken(Auth auth) {
        return Jwts.builder()
                .subject(auth.getId().toString())
                .claim("email", auth.getEmail())
                .issuedAt(new Date())
                .expiration(Date.from(
                        Instant.now().plus(this.expiration)
                ))
                .signWith(key)
                .compact();
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .verifyWith(this.key)
                .build()
                .parseSignedClaims(token);
    }
}
