package com.fenris06.applicationmanager.service.impl;

import com.fenris06.applicationmanager.model.User;
import com.fenris06.applicationmanager.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
@Service
public class JwtServiceImpl implements JwtService {

    private final String secret;
    private final String issuer;
    private final int tokenTtlHours;

    public JwtServiceImpl(@Value("${jwt.secret}") String secret,
                          @Value("${spring.application.name}") String issuer,
                          @Value("${jwt.token.ttl_hours}") int tokenTtlHours) {
        this.secret = secret;
        this.issuer = issuer;
        this.tokenTtlHours = tokenTtlHours;
    }

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuer(issuer)
                .setExpiration(Date.from(Instant.now().plus(tokenTtlHours, ChronoUnit.HOURS)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
