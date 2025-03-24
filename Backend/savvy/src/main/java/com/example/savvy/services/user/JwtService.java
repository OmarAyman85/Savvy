package com.example.savvy.services.user;

import com.example.savvy.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private final String secretKey = "ea452c68ecdea04998e840a3cfdeb91b745d4f489eaf62ed51cb24fff8f0e4aa";

    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValidToken(String token, UserDetails user) {
        String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    public String generateToken(User user) {

        return Jwts.builder().subject(user.getUsername()).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)).signWith(getSigningKey()).compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
