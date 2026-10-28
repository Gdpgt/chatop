package com.chatop.backend.service;

import org.springframework.stereotype.Service;

import com.chatop.backend.domain.exception.UserNotFoundException;
import com.chatop.backend.domain.model.User;
import com.chatop.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public User getById(int id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
