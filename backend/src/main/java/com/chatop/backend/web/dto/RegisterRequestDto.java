package com.chatop.backend.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(

  @NotBlank(message = "The email is mandatory.")
  @Size(max = 255, message = "The email must not exceed 255 characters.")
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,20}$", message = "Invalid email format.")
  String email,

  @NotBlank(message = "The name is mandatory.")
  @Size(min = 3, max = 50, message = "The name must contain between 3 and 50 characters.")
  @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\-'’ ]+$", message = "The name contains unauthorized characters.")
  String name,

  @NotBlank(message = "The password is mandatory.")
  @Size(min = 3, max = 72, message = "The password must contain between 3 and 72 characters.")
  String password
) {

  @Override
  public String toString() {
    return "UserRegistrationDto[email=" + email + ", name=" + name + "]";
  }
}
