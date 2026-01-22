package edu.esiea.tp_fil_rouge.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Représentation d'une leçon")
public record LessonDto(@Schema(description = "Identifiant unique de la leçon", example = "123")
                        Long id,
                        @Schema(description = "Titre de la leçon", example = "Introduction à Java")
                        @NotBlank(message = "Le titre est obligatoire")
                        String title){}
