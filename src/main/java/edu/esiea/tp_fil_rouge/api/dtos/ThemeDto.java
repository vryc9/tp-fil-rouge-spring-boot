package edu.esiea.tp_fil_rouge.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record ThemeDto(Long id, @NotBlank(message = "Le nom est obligatoire") String name) {
}
