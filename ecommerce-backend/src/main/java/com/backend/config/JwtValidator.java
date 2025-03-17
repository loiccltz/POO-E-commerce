package com.backend.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidator extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // recup le token dans le Header
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        // on check si on token existe, et on retire les 7 premier charactères
        // c'est souvent "Bearer " et on en a pas besoin
        if(jwt!=null){
            jwt=jwt.substring(7);
            try {
                // verifier si le token est bon grace a la clé secrete
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

                // on decode les données contenue dans le token
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                // on recupere l'email ainsi que le role de l'utilisateur
                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));

                
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                Authentication authentication = new UsernamePasswordAuthenticationToken(email,null, auths);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
               throw new BadCredentialsException("token invalide dans JwtValidator");
            }
        }

        filterChain.doFilter(request, response);
    }

}
