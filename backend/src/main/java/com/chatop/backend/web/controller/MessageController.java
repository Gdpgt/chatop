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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Messages", description = "Messages envoyés aux propriétaires")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    /**
     * Stores a message sent to the owner of a rental.
     *
     * @param dto target rental id and message content
     * @param authentication sender, taken from the JWT
     * @return a confirmation message; 404 if the rental does not exist
     */
    @Operation(summary = "Envoyer un message au propriétaire d'une location",
               description = "Expéditeur = utilisateur du JWT. Le champ user_id envoyé par le front est ignoré.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Message envoyé"),
        @ApiResponse(responseCode = "400", description = "Champ invalide ou body illisible", content = @Content),
        @ApiResponse(responseCode = "401", description = "JWT absent ou invalide", content = @Content),
        @ApiResponse(responseCode = "404", description = "Location inconnue", content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto send(@RequestBody @Valid MessageRequestDto dto, Authentication authentication) {
        messageService.send(dto.rentalId(), dto.message(), authentication.getName());
        return new MessageResponseDto("Message sent with success");
    }

}
