package com.frank.authservice.services.impl;

import com.frank.authservice.common.dtos.LoginRequest;
import com.frank.authservice.common.dtos.TokenResponse;
import com.frank.authservice.common.dtos.UserRequest;
import com.frank.authservice.common.entities.UserModel;
import com.frank.authservice.repositories.UserRepository;
import com.frank.authservice.services.AuthService;
import com.frank.authservice.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenResponse createUser(UserRequest userRequest) {
        //String encodePassword = passwordEncoder.
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getId()))
                .orElseThrow(() -> new RuntimeException("Error creating user!"));
    }

    @Override
    public TokenResponse loginUser(LoginRequest loginRequest) {
        return userRepository.findByEmail(loginRequest.getEmail())
        .filter(user -> passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
        .map(user -> jwtService.generateToken(user.getId()))
        .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));      
    }

    private UserModel mapToEntity(UserRequest userRequest) {
        return UserModel.builder()
                .email(userRequest.getEmail())
                .password(this.passwordEncoder.encode(userRequest.getPassword()))
                .role("USER")
                .build();
    }
}
