package com.chatop.backend.web.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RentalResponseDto(

    Integer id,

    String name,

    Integer surface,

    Integer price,

    String picture,

    String description,

    @JsonProperty("owner_id")
    Integer ownerId,

    @JsonProperty("created_at")
    LocalDateTime createdAt,

    @JsonProperty("updated_at")
    LocalDateTime updatedAt
) {}
