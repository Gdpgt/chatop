package com.chatop.backend.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.chatop.backend.validation.ValidationPatterns.*;

// The user_id sent by the front is deliberately not mapped: the sender comes from the JWT
public record MessageRequestDto(

  @JsonProperty("rental_id")
  @NotNull(message = RENTAL_MANDATORY_MESSAGE)
  Integer rentalId,

  @NotBlank(message = MESSAGE_MANDATORY_MESSAGE)
  @Size(max = MESSAGE_MAX, message = MESSAGE_SIZE_MESSAGE)
  @Pattern(regexp = FREE_TEXT_REGEX, message = MESSAGE_FORMAT_MESSAGE)
  String message
) {

  public MessageRequestDto {
    message = message == null ? null : message.strip();
  }
}
