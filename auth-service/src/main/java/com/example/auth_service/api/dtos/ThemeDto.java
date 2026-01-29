package com.example.auth_service.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record ThemeDto(Long id, @NotBlank(message = "Le nom est obligatoire") String name) {
}
