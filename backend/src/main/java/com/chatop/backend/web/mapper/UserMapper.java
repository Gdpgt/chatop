package com.chatop.backend.web.mapper;

import com.chatop.backend.domain.model.User;
import com.chatop.backend.web.dto.RegisterRequestDto;
import com.chatop.backend.web.dto.UserResponseDto;

public class UserMapper {

    private UserMapper() {}

    public static User toEntity(RegisterRequestDto dto) {
        return new User()
                .setName(dto.name())
                .setEmail(dto.email())
                .setPassword(dto.password());
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }

}
