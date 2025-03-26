package com.backend.ecommerce_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.ecommerce_backend.config.JwtProvider;
import com.backend.ecommerce_backend.entity.User;
import com.backend.ecommerce_backend.exception.UserException;
import com.backend.ecommerce_backend.repository.UserRepository;
import com.backend.ecommerce_backend.request.LoginRequest;
import com.backend.ecommerce_backend.response.AuthResponse;
import com.backend.ecommerce_backend.service.CustomUserServiceImplementation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Operation de connexion et inscription")
public class AuthController {

    private UserRepository userRepository;
    private JwtProvider JwtProvider;
    private PasswordEncoder passwordEncoder;
    private CustomUserServiceImplementation customUserService;

    public AuthController(UserRepository userRepository, CustomUserServiceImplementation customUserService, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.customUserService=customUserService;
        this.passwordEncoder=passwordEncoder;
        this.JwtProvider=jwtProvider;
    }

    @PostMapping("/register")
    @Operation(
        summary = "enregistre un nouvel utilisateur", 
        description = "enregistre un nouvel utilisateur",
        responses = {
            @ApiResponse(
                responseCode = "201", 
                description = "Utilisateur enregistré",
                content = @Content(schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                responseCode = "400", 
                description = "Email déjà utilisé"
            )
        }
    )
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

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("connexion effectué");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    @Operation(
        summary = "Connexion", 
        description = "Connexion",
        responses = {
            @ApiResponse(
                responseCode = "201", 
                description = "Connecté",
                content = @Content(schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                responseCode = "401", 
                description = "Mot de passe ou identifiant invalide"
            )
        }
    )
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