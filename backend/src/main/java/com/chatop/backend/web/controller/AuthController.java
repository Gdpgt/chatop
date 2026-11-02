package com.chatop.backend.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.backend.service.AuthService;
import com.chatop.backend.service.UserService;
import com.chatop.backend.web.dto.AuthResponseDto;
import com.chatop.backend.web.dto.LoginRequestDto;
import com.chatop.backend.web.dto.RegisterRequestDto;
import com.chatop.backend.web.dto.UserResponseDto;
import com.chatop.backend.web.mapper.UserMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Authentification", description = "Inscription, connexion et utilisateur courant")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @Operation(summary = "Créer un compte", description = "Route publique. Renvoie un JWT.")
    @SecurityRequirements
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Compte créé"),
        @ApiResponse(responseCode = "400", description = "Champ invalide ou email déjà utilisé", content = @Content)
    })
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponseDto register(@RequestBody @Valid RegisterRequestDto dto) {
        String token = authService.register(UserMapper.toEntity(dto));
        return new AuthResponseDto(token);
    }

    @Operation(summary = "Se connecter", description = "Route publique. Renvoie un JWT à utiliser dans « Authorize ».")
    @SecurityRequirements
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Authentification réussie"),
        @ApiResponse(responseCode = "400", description = "Champ manquant ou body illisible", content = @Content),
        @ApiResponse(responseCode = "401", description = "Identifiants incorrects", content = @Content)
    })
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponseDto login(@RequestBody @Valid LoginRequestDto dto) {
        String token = authService.login(dto.email(), dto.password());
        return new AuthResponseDto(token);
    }

    @Operation(summary = "Récupérer l'utilisateur connecté", description = "Utilisateur déduit du JWT.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Utilisateur trouvé"),
        @ApiResponse(responseCode = "401", description = "JWT absent ou invalide", content = @Content)
    })
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getCurrentUser(Authentication authentication) {
        return UserMapper.toDto(userService.getByEmail(authentication.getName()));
    }
}
