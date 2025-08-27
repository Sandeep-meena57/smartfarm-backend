package com.smartfarm.smartfarm.controller;


import com.smartfarm.smartfarm.DTO.AuthResponse;
import com.smartfarm.smartfarm.DTO.LoginRequest;
import com.smartfarm.smartfarm.DTO.RegisterRequest;
import com.smartfarm.smartfarm.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "For registration of user/Farmer")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "For Login with valid credential of user/Farmer")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}

