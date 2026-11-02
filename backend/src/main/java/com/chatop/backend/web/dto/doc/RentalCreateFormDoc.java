package com.chatop.backend.web.dto.doc;

import com.chatop.backend.web.dto.RentalRequestDto;

import io.swagger.v3.oas.annotations.media.Schema;

// OpenAPI-only schema: springdoc drops the @RequestPart file when merged with a @ModelAttribute
@Schema(name = "RentalCreateForm", allOf = RentalRequestDto.class, requiredProperties = {"picture"})
public record RentalCreateFormDoc(

    @Schema(description = "Image de la location", type = "string", format = "binary")
    String picture
) {}
