package com.example.jwt_auth.service;


import com.example.jwt_auth.DTO.AuthenicateRequest;
import com.example.jwt_auth.DTO.AuthenticatedResponse;
import com.example.jwt_auth.DTO.RegisterRequest;
import com.example.jwt_auth.model.Role;
import com.example.jwt_auth.model.User;
import com.example.jwt_auth.repo.UserRepo;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticatedResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFisrtName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatedResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticatedResponse authenticate(AuthenicateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatedResponse.builder()
                .token(jwtToken)
                .build();

    }
}
