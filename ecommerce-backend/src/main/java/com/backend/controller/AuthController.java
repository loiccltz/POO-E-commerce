package com.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.config.JwtProvider;
import com.backend.entity.User;
import com.backend.exception.UserException;
import com.backend.repository.UserRepository;
import com.backend.request.LoginRequest;
import com.backend.response.AuthResponse;
import com.backend.service.CustomUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    private JwtProvider JwtProvider;
    private PasswordEncoder passwordEncoder;
    private CustomUserServiceImplementation customUserService;

    public AuthController(UserRepository userRepository, CustomUserServiceImplementation customUserService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customUserService=customUserService;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {

        String email = user.getEmail();
        String passsword = user.getPassword();
        String username = user.getUsername();

        User isEmailExist = userRepository.findByEmail(email);

        if (isEmailExist != null) {
            throw new UserException("Email deja utilisé par un autre compte");
        }

        User createdUser = new User();

        createdUser.setEmail(email);
        createdUser.setUsername(username);
        createdUser.setPassword(passwordEncoder.encode(passsword));

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
                savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token, "la connexion a bien marché");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token, "la connexion a bien marché");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) {
      
        UserDetails userDetails = customUserService.loadUserByUsername(username);

        if(userDetails==null){
            throw new BadCredentialsException("Mauvais nom d'utilisateur");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("mo de passe invalide");
        }

        return new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities());
    }

}
