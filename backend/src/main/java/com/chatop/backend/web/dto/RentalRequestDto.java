package com.chatop.backend.web.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.chatop.backend.validation.ValidationPatterns.*;

public record RentalRequestDto(

  @Schema(example = "Maison de campagne")
  @NotBlank(message = NAME_MANDATORY_MESSAGE)
  @Size(min = RENTAL_NAME_MIN, max = RENTAL_NAME_MAX, message = RENTAL_NAME_SIZE_MESSAGE)
  @Pattern(regexp = SINGLE_LINE_TEXT_REGEX, message = RENTAL_NAME_FORMAT_MESSAGE)
  String name,

  @Schema(description = "Surface en m²", example = "120")
  @NotNull(message = SURFACE_MANDATORY_MESSAGE)
  @Min(value = SURFACE_MIN, message = SURFACE_RANGE_MESSAGE)
  @Max(value = SURFACE_MAX, message = SURFACE_RANGE_MESSAGE)
  BigDecimal surface,

  @Schema(description = "Prix par mois", example = "850")
  @NotNull(message = PRICE_MANDATORY_MESSAGE)
  @Min(value = PRICE_MIN, message = PRICE_RANGE_MESSAGE)
  @Max(value = PRICE_MAX, message = PRICE_RANGE_MESSAGE)
  BigDecimal price,

  @Schema(example = "Maison lumineuse avec jardin.")
  @NotBlank(message = DESCRIPTION_MANDATORY_MESSAGE)
  @Size(max = DESCRIPTION_MAX, message = DESCRIPTION_SIZE_MESSAGE)
  @Pattern(regexp = FREE_TEXT_REGEX, message = DESCRIPTION_FORMAT_MESSAGE)
  String description
) {

  public RentalRequestDto {
    name = name == null ? null : name.strip();
    description = description == null ? null : description.strip();
  }
}
