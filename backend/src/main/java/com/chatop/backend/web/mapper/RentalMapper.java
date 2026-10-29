package com.chatop.backend.web.mapper;

import java.util.List;

import com.chatop.backend.domain.model.Rental;
import com.chatop.backend.web.dto.RentalListResponseDto;
import com.chatop.backend.web.dto.RentalResponseDto;

public class RentalMapper {

    private RentalMapper() {}

    public static RentalResponseDto toDto(Rental rental) {
        return new RentalResponseDto(
            rental.getId(),
            rental.getName(),
            rental.getSurface(),
            rental.getPrice(),
            rental.getPicture(),
            rental.getDescription(),
            rental.getOwnerId(),
            rental.getCreatedAt(),
            rental.getUpdatedAt()
        );
    }

    public static RentalListResponseDto toListDto(List<Rental> rentals) {
        return new RentalListResponseDto(
            rentals.stream().map(RentalMapper::toDto).toList()
        );
    }

}
