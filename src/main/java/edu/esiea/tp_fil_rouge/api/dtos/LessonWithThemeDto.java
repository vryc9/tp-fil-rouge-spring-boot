package edu.esiea.tp_fil_rouge.api.dtos;


import io.swagger.v3.oas.annotations.media.Schema;

public record LessonWithThemeDto(Long id, String title,
                                 @Schema(implementation = ThemeDto.class)
                                 ThemeDto theme) {
}
