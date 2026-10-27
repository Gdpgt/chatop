package com.chatop.backend.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.backend.service.AuthService;
import com.chatop.backend.web.dto.AuthResponseDto;
import com.chatop.backend.web.dto.LoginRequestDto;
import com.chatop.backend.web.dto.RegisterRequestDto;
import com.chatop.backend.web.mapper.UserMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponseDto register(@RequestBody @Valid RegisterRequestDto dto) {
        String token = authService.register(UserMapper.toEntity(dto));
        return new AuthResponseDto(token);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponseDto login(@RequestBody @Valid LoginRequestDto dto) {
        String token = authService.login(dto.email(), dto.password());
        return new AuthResponseDto(token);
    }
    
    
}
