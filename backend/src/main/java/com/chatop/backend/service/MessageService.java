package com.chatop.backend.service;

import org.springframework.stereotype.Service;

import com.chatop.backend.domain.model.Message;
import com.chatop.backend.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final RentalService rentalService;
    private final UserService userService;

    public void send(int rentalId, String content, String senderEmail) {
        Integer existingRentalId = rentalService.getById(rentalId).getId();
        Integer senderId = userService.getByEmail(senderEmail).getId();
        messageRepository.save(new Message()
                .setRentalId(existingRentalId)
                .setUserId(senderId)
                .setMessage(content));
    }
}
