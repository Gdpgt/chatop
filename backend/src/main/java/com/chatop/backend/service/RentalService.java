package com.chatop.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.backend.domain.exception.NotRentalOwnerException;
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

    @Transactional
    public void update(int id, Rental changes, String requesterEmail) {
        Rental rental = getById(id);
        assertOwner(rental, requesterEmail);
        rental.setName(changes.getName())
                .setSurface(changes.getSurface())
                .setPrice(changes.getPrice())
                .setDescription(changes.getDescription());
    }

    @Transactional
    public void delete(int id, String requesterEmail) {
        Rental rental = getById(id);
        assertOwner(rental, requesterEmail);
        rentalRepository.delete(rental);
        // Flush first: a failing SQL delete must not leave the rental without its image
        rentalRepository.flush();
        imageStorageService.delete(rental.getPicture());
    }

    private void assertOwner(Rental rental, String requesterEmail) {
        if (!rental.getOwnerId().equals(userService.getByEmail(requesterEmail).getId())) {
            throw new NotRentalOwnerException();
        }
    }
}
