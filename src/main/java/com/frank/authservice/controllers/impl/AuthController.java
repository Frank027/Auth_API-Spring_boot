package com.frank.authservice.controllers.impl;

import com.frank.authservice.common.dtos.LoginRequest;
import com.frank.authservice.common.dtos.TokenResponse;
import com.frank.authservice.common.dtos.UserRequest;
import com.frank.authservice.controllers.AuthApi;
import com.frank.authservice.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    private  final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<TokenResponse> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }

    @Override
    public ResponseEntity<TokenResponse> loginUser(LoginRequest loginRequest) {
        return ResponseEntity.ok(this.authService.loginUser(loginRequest));
    }

    @Override
    public ResponseEntity<String> getUser(String userId) {
        return ResponseEntity.ok(userId);
    }
}
