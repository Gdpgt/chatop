package com.chatop.backend.domain.exception;

public class RentalNotFoundException extends RuntimeException {

    public RentalNotFoundException() {
        super("Rental does not exist.");
    }

}
