package com.example.auth_service.api.dtos;


import io.swagger.v3.oas.annotations.media.Schema;

public record LessonWithThemeDto(Long id, String title,
                                 @Schema(implementation = ThemeDto.class)
                                 ThemeDto theme) {
}
