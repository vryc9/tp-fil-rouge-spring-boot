package edu.esiea.tp_fil_rouge.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record LessonDto(Long id, @NotBlank(message = "Le titre est obligatoire") String title){}
