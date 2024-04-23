package com.weather.management.service;

import com.weather.management.dto.LoginDto;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<?> authenticateUser(LoginDto loginDto);
}
