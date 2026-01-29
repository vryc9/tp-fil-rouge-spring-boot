package com.example.auth_service.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO pour la requête de login")
public record LoginRequestDto(
        @Schema(description = "Nom d'utilisateur", example = "user1") String username,
        @Schema(description = "Mot de passe", example = "password123") String password
) {
}

