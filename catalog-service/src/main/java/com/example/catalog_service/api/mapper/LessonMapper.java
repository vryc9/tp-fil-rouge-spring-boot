package com.example.catalog_service.api.mapper;
import com.example.catalog_service.api.dtos.LessonDto;
import com.example.catalog_service.api.dtos.LessonWithThemeDto;
import com.example.catalog_service.domain.models.Lesson;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    LessonDto toDto(Lesson lesson);
    Lesson toEntity(LessonDto dto);
    LessonWithThemeDto toDtoWithTheme(Lesson lesson);
}
