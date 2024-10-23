package com.frank.authservice.services;

import com.frank.authservice.common.dtos.TokenResponse;
import com.frank.authservice.common.dtos.UserRequest;

public interface AuthService {
    TokenResponse createUser(UserRequest userRequest);
}
