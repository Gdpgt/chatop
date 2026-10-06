package com.chatop.backend.web.dto;

public record AuthResponseDto(String token) {

  @Override
  public String toString() {
    return "AuthResponseDto[token=***]";
  }
}
