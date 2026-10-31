package com.chatop.backend.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.backend.service.MessageService;
import com.chatop.backend.web.dto.MessageRequestDto;
import com.chatop.backend.web.dto.MessageResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto send(@RequestBody @Valid MessageRequestDto dto, Authentication authentication) {
        messageService.send(dto.rentalId(), dto.message(), authentication.getName());
        return new MessageResponseDto("Message sent with success");
    }

}
