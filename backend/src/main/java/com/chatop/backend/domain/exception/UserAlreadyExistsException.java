package com.chatop.backend.domain.exception;

public class UserAlreadyExistsException extends RuntimeException {
    

    public UserAlreadyExistsException() {
        super("User already exists.");
    }

}