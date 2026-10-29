package com.chatop.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chatop.backend.domain.exception.RentalNotFoundException;
import com.chatop.backend.domain.model.Rental;
import com.chatop.backend.repository.RentalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;

    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    public Rental getById(int id) {
        return rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);
    }
}
