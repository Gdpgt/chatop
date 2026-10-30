package com.chatop.backend.domain.exception;

public class InvalidImageException extends RuntimeException {

    public InvalidImageException() {
        super("The picture must be a non-empty JPEG, PNG, GIF or WebP image.");
    }

}
