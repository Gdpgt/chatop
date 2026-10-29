package com.chatop.backend.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.backend.service.RentalService;
import com.chatop.backend.web.dto.RentalListResponseDto;
import com.chatop.backend.web.dto.RentalResponseDto;
import com.chatop.backend.web.mapper.RentalMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RentalListResponseDto getAll() {
        return RentalMapper.toListDto(rentalService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalResponseDto getById(@PathVariable int id) {
        return RentalMapper.toDto(rentalService.getById(id));
    }

}
