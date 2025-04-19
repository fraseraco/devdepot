package com.swe.backend.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private final Key key;
    private final Duration expiry;

    public JwtUtil(
            @Value("${jwt.secret}") String base64Secret,
            @Value("${jwt.expiration-minutes:60}") long expMinutes) {

        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Secret));
        this.expiry = Duration.ofMinutes(expMinutes);
    }

    /** Build a signed HS‑256 JWT. */
    public String generate(String username, List<String> roles) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expiry)))
                .signWith(key)   // new (non‑deprecated) style
                .compact();
    }
}