package com.frank.authservice.controllers;

import com.frank.authservice.common.constants.ApiPAthConstants;
import com.frank.authservice.common.dtos.TokenResponse;
import com.frank.authservice.common.dtos.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPAthConstants.V1_ROUTE + ApiPAthConstants.AUTH_ROUTE )
public interface AuthApi {
    @PostMapping(value = "/register")
    ResponseEntity<TokenResponse> createUser(@RequestBody @Valid UserRequest userRequest);

    @GetMapping
    ResponseEntity<String> getUser(@RequestAttribute(name = "X-User-Id") String userId);
}
