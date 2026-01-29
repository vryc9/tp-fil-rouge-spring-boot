package com.example.catalog_service.api.mapper;

import com.example.catalog_service.api.dtos.ThemeDto;
import com.example.catalog_service.domain.models.Theme;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ThemeMapper {
    ThemeDto toDto(Theme theme);
    Theme toEntity(ThemeDto dto);
}