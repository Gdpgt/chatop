package com.chatop.backend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatop.backend.domain.exception.UserAlreadyExistsException;
import com.chatop.backend.domain.model.User;
import com.chatop.backend.repository.UserRepository;
import com.chatop.backend.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return jwtService.generateJwt(user.getEmail());
    }

    public String login(String email, String password) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );
        return jwtService.generateJwt(email);
    }

}
