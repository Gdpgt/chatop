package com.chatop.backend.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.chatop.backend.validation.ValidationPatterns.*;

// The user_id sent by the front is deliberately not mapped: the sender comes from the JWT
public record MessageRequestDto(

  @Schema(description = "Identifiant de la location concernée", example = "3")
  @JsonProperty("rental_id")
  @NotNull(message = RENTAL_MANDATORY_MESSAGE)
  Integer rentalId,

  @Schema(example = "Bonjour, la location est-elle disponible en juillet ?")
  @NotBlank(message = MESSAGE_MANDATORY_MESSAGE)
  @Size(max = MESSAGE_MAX, message = MESSAGE_SIZE_MESSAGE)
  @Pattern(regexp = FREE_TEXT_REGEX, message = MESSAGE_FORMAT_MESSAGE)
  String message
) {

  public MessageRequestDto {
    message = message == null ? null : message.strip();
  }
}
