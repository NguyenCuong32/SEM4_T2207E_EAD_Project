package com.fai.service.brofee.controller;

import com.fai.service.brofee.dto.AuthenticationResponse;
import com.fai.service.brofee.dto.LoginRequest;
import com.fai.service.brofee.dto.UserCreateDTO;
import com.fai.service.brofee.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid UserCreateDTO dto) {
        authService.signup(dto);
        return new ResponseEntity<>("User Registration Successful", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
