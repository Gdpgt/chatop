package com.chatop.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.backend.domain.exception.RentalNotFoundException;
import com.chatop.backend.domain.model.Rental;
import com.chatop.backend.repository.RentalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserService userService;
    private final ImageStorageService imageStorageService;

    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    public Rental getById(int id) {
        return rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);
    }

    public void create(Rental rental, MultipartFile picture, String ownerEmail) {
        // Owner resolved before storing, so an unknown user leaves no orphan file
        rental.setOwnerId(userService.getByEmail(ownerEmail).getId());
        rental.setPicture(imageStorageService.store(picture));
        rentalRepository.save(rental);
    }
}
