package com.chatop.backend.web.advice;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.chatop.backend.domain.exception.InvalidImageException;
import com.chatop.backend.domain.exception.NotRentalOwnerException;
import com.chatop.backend.domain.exception.RentalNotFoundException;
import com.chatop.backend.domain.exception.UserAlreadyExistsException;
import com.chatop.backend.domain.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public Map<String, Object> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        log.warn("This user already exists in the database.");
        return Map.of();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public Map<String, Object> handleBadCredentialsException(BadCredentialsException e) {
        log.warn("Authentication failed: bad credentials.");
        return Map.of();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public Map<String, Object> handleUserNotFoundException(UserNotFoundException e) {
        log.warn("This user does not exist in the database.");
        return Map.of();
    }

    @ExceptionHandler(RentalNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public Map<String, Object> handleRentalNotFoundException(RentalNotFoundException e) {
        log.warn("This rental does not exist in the database.");
        return Map.of();
    }

    @ExceptionHandler(NotRentalOwnerException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    public Map<String, Object> handleNotRentalOwnerException(NotRentalOwnerException e) {
        log.warn("Only the owner can modify or delete this rental.");
        return Map.of();
    }

    @ExceptionHandler(InvalidImageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public Map<String, Object> handleInvalidImageException(InvalidImageException e) {
        log.warn("Invalid picture upload.");
        return Map.of();
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public Map<String, Object> handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        log.warn("Missing multipart part '{}'", e.getRequestPartName());
        return Map.of();
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE) // 413
    public Map<String, Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.warn("Upload exceeds the maximum allowed size");
        return Map.of();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> fields = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getField)
                .toList();
        log.warn("Validation failed on fields : {}", fields);
        return Map.of();
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public Map<String, Object> handleNoResourceFoundException(NoResourceFoundException e) {
        log.warn("No resource found for {}", e.getResourcePath());
        return Map.of();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public Map<String, Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.warn("Invalid type for parameter '{}'", e.getName());
        return Map.of();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) // 405
    public Map<String, Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.warn("HTTP method '{}' not supported", e.getMethod());
        return Map.of();
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE) // 415
    public Map<String, Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.warn("Content type '{}' not supported", e.getContentType());
        return Map.of();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public Map<String, Object> handleUnexpectedError(Exception e) {
        log.error("Unexpected internal error", e);
        return Map.of();
    }
    
}
