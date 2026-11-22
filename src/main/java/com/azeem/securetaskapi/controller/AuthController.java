package com.azeem.securetaskapi.controller;

import com.azeem.securetaskapi.dto.AuthResponse;
import com.azeem.securetaskapi.dto.LoginRequest;
import com.azeem.securetaskapi.dto.RegisterRequest;
import com.azeem.securetaskapi.dto.UserProfileResponse;
import com.azeem.securetaskapi.jwt.JwtService;
import com.azeem.securetaskapi.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService,JwtService jwtService
    ){
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authService.login(request);
        String token = jwtService.generateToken(authentication);
        AuthResponse response = new AuthResponse(token,"Bearer");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserProfileResponse> getCurrUser(@AuthenticationPrincipal Jwt jwt){
        Long userId = jwt.getClaim("userId");
        String username = jwt.getSubject();
        List<String> roles = jwt.getClaim("roles");

        UserProfileResponse response =
                new UserProfileResponse(userId, username, roles);

        return ResponseEntity.ok(response);
    }
}
