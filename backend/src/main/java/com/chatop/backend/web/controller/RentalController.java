package com.chatop.backend.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.backend.service.RentalService;
import com.chatop.backend.web.dto.MessageResponseDto;
import com.chatop.backend.web.dto.RentalListResponseDto;
import com.chatop.backend.web.dto.RentalRequestDto;
import com.chatop.backend.web.dto.RentalResponseDto;
import com.chatop.backend.web.mapper.RentalMapper;

import jakarta.validation.Valid;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto create(@ModelAttribute @Valid RentalRequestDto dto,
                                     @RequestPart("picture") MultipartFile picture,
                                     Authentication authentication) {
        rentalService.create(RentalMapper.toEntity(dto), picture, authentication.getName());
        return new MessageResponseDto("Rental created !");
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDto update(@PathVariable int id,
                                     @ModelAttribute @Valid RentalRequestDto dto,
                                     Authentication authentication) {
        rentalService.update(id, RentalMapper.toEntity(dto), authentication.getName());
        return new MessageResponseDto("Rental updated !");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, Authentication authentication) {
        rentalService.delete(id, authentication.getName());
    }

}
