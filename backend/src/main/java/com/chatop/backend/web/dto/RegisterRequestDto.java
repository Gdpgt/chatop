package com.chatop.backend.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.chatop.backend.validation.ValidationPatterns.*;

public record RegisterRequestDto(

  @NotBlank(message = EMAIL_MANDATORY_MESSAGE)
  @Size(max = EMAIL_MAX, message = EMAIL_SIZE_MESSAGE)
  @Pattern(regexp = EMAIL_REGEX, message = EMAIL_FORMAT_MESSAGE)
  String email,

  @NotBlank(message = NAME_MANDATORY_MESSAGE)
  @Size(min = NAME_MIN, max = NAME_MAX, message = NAME_SIZE_MESSAGE)
  @Pattern(regexp = NAME_REGEX, message = NAME_FORMAT_MESSAGE)
  String name,

  @NotBlank(message = PASSWORD_MANDATORY_MESSAGE)
  @Size(min = 3, max = 72, message = "The password must contain between 3 and 72 characters.")
  String password
) {

  @Override
  public String toString() {
    return "RegisterRequestDto[email=" + email + ", name=" + name + "]";
  }
}
