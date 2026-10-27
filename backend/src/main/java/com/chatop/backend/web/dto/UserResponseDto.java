package com.chatop.backend.web.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseDto(

    Integer id,

    String name,

    String email,

    @JsonProperty("created_at") 
    LocalDateTime createdAt,

    @JsonProperty("updated_at") 
    LocalDateTime updatedAt
) {}
