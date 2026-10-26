package com.chatop.backend.domain.exception;

public class UserNotFoundException extends RuntimeException {
    

    public UserNotFoundException() {
        super("User does not exist.");
    }

}