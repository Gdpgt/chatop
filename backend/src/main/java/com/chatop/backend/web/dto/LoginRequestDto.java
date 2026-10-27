package com.chatop.backend.web.dto;

import jakarta.validation.constraints.NotBlank;

import static com.chatop.backend.validation.ValidationPatterns.*;

public record LoginRequestDto(

    @NotBlank(message = EMAIL_MANDATORY_MESSAGE)
    String email,

    @NotBlank(message = PASSWORD_MANDATORY_MESSAGE)
    String password

){

    @Override
    public String toString() {
      return "LoginRequestDto[email=" + email + "]";
    }
}
