package edu.esiea.tp_fil_rouge.api.mapper;

import edu.esiea.tp_fil_rouge.api.dtos.ThemeDto;
import edu.esiea.tp_fil_rouge.domain.models.Theme;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ThemeMapper {
    ThemeDto toDto(Theme theme);
    Theme toEntity(ThemeDto dto);
}