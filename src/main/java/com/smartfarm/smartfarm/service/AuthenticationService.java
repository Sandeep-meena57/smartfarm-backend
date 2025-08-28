package com.smartfarm.smartfarm.service;


import com.smartfarm.smartfarm.DTO.AuthResponse;
import com.smartfarm.smartfarm.DTO.LoginRequest;
import com.smartfarm.smartfarm.DTO.RegisterRequest;
import com.smartfarm.smartfarm.Security.JwtService;
import com.smartfarm.smartfarm.entity.Role;
import com.smartfarm.smartfarm.entity.User;
import com.smartfarm.smartfarm.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                //.role(Role.FARMER)
                .build();
        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse("User Registered Successfully",token,user.getEmail());
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse("User Login Successfully",token,user.getEmail());
    }
}
