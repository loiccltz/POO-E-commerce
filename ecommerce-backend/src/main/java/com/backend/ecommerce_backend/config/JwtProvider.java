package com.backend.ecommerce_backend.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    public String generateToken(Authentication auth){
        String jwt= Jwts.builder()
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime()+8460000))
            .claim("email", auth.getName())
            .signWith(key).compact();

        return jwt;
    }
}
