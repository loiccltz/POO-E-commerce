package com.backend.service;

import com.backend.config.JwtProvider;
import com.backend.entity.User;
import com.backend.exception.UserException;
import com.backend.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import com.backend.config.JwtConstant;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserException("Utilisateur avec l'ID " + userId + " non trouvé"));
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        // Retirer le préfixe "Bearer "
        jwt = jwt.substring(7);
        
        try {
            SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
            
            String email = String.valueOf(claims.get("email"));
            
            User user = userRepository.findByEmail(email);
            
            if (user == null) {
                throw new UserException("Utilisateur avec l'email " + email + " non trouvé");
            }
            
            return user;
        } catch (Exception e) {
            throw new UserException("Token JWT invalide: " + e.getMessage());
        }
    }
}