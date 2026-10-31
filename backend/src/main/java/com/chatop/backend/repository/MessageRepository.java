package com.chatop.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.chatop.backend.domain.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Modifying
    @Query("DELETE FROM Message m WHERE m.rentalId = :rentalId")
    void deleteByRentalId(Integer rentalId);

}
