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
import com.chatop.backend.web.dto.doc.RentalCreateFormDoc;
import com.chatop.backend.web.mapper.RentalMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Locations", description = "Consultation et gestion des locations")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    @Operation(summary = "Lister toutes les locations")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Liste des locations"),
        @ApiResponse(responseCode = "401", description = "JWT absent ou invalide", content = @Content)
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RentalListResponseDto getAll() {
        return RentalMapper.toListDto(rentalService.getAll());
    }

    @Operation(summary = "Récupérer une location")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Location trouvée"),
        @ApiResponse(responseCode = "400", description = "Identifiant non numérique", content = @Content),
        @ApiResponse(responseCode = "401", description = "JWT absent ou invalide", content = @Content),
        @ApiResponse(responseCode = "404", description = "Location inconnue", content = @Content)
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalResponseDto getById(@Parameter(description = "Identifiant de la location", example = "3")
                                     @PathVariable int id) {
        return RentalMapper.toDto(rentalService.getById(id));
    }

    @Operation(summary = "Créer une location",
               description = "Formulaire multipart avec image obligatoire. Propriétaire = utilisateur du JWT.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Location créée"),
        @ApiResponse(responseCode = "400", description = "Champ invalide, image manquante ou de type non supporté",
                     content = @Content),
        @ApiResponse(responseCode = "401", description = "JWT absent ou invalide", content = @Content),
        @ApiResponse(responseCode = "413", description = "Image trop volumineuse", content = @Content)
    })
    @RequestBody(required = true, content = @Content(
        mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
        schema = @Schema(implementation = RentalCreateFormDoc.class)))
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto create(@ModelAttribute @Valid RentalRequestDto dto,
                                     @RequestPart("picture") MultipartFile picture,
                                     Authentication authentication) {
        rentalService.create(RentalMapper.toEntity(dto), picture, authentication.getName());
        return new MessageResponseDto("Rental created !");
    }

    @Operation(summary = "Modifier une location",
               description = "Formulaire multipart sans image : l'image existante est conservée. "
                           + "Propriétaire uniquement.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Location modifiée"),
        @ApiResponse(responseCode = "400", description = "Champ invalide ou identifiant non numérique",
                     content = @Content),
        @ApiResponse(responseCode = "401", description = "JWT absent ou invalide", content = @Content),
        @ApiResponse(responseCode = "403", description = "L'utilisateur n'est pas le propriétaire", content = @Content),
        @ApiResponse(responseCode = "404", description = "Location inconnue", content = @Content)
    })
    @RequestBody(required = true, content = @Content(
        mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
        schema = @Schema(implementation = RentalRequestDto.class)))
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDto update(@Parameter(description = "Identifiant de la location", example = "1")
                                     @PathVariable int id,
                                     @ModelAttribute @Valid RentalRequestDto dto,
                                     Authentication authentication) {
        rentalService.update(id, RentalMapper.toEntity(dto), authentication.getName());
        return new MessageResponseDto("Rental updated !");
    }

    @Operation(summary = "Supprimer une location", description = "Propriétaire uniquement. Non appelée par le front.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Location supprimée"),
        @ApiResponse(responseCode = "400", description = "Identifiant non numérique", content = @Content),
        @ApiResponse(responseCode = "401", description = "JWT absent ou invalide", content = @Content),
        @ApiResponse(responseCode = "403", description = "L'utilisateur n'est pas le propriétaire", content = @Content),
        @ApiResponse(responseCode = "404", description = "Location inconnue", content = @Content)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Parameter(description = "Identifiant de la location", example = "1")
                       @PathVariable int id,
                       Authentication authentication) {
        rentalService.delete(id, authentication.getName());
    }

}
