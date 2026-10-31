package com.chatop.backend.domain.exception;

public class NotRentalOwnerException extends RuntimeException {

    public NotRentalOwnerException() {
        super("User is not the owner of this rental.");
    }

}
