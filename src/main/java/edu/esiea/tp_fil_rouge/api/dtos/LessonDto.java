package edu.esiea.tp_fil_rouge.api.dtos;

import edu.esiea.tp_fil_rouge.domain.models.Theme;

public record LessonDto(Long id, String title, Theme theme){}
