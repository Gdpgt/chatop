package com.chatop.backend.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import static com.chatop.backend.validation.ValidationPatterns.*;

public record LoginRequestDto(

    @Schema(example = "test@test.com")
    @NotBlank(message = EMAIL_MANDATORY_MESSAGE)
    String email,

    @Schema(example = "test")
    @NotBlank(message = PASSWORD_MANDATORY_MESSAGE)
    String password

){

    @Override
    public String toString() {
      return "LoginRequestDto[email=" + email + "]";
    }
}
