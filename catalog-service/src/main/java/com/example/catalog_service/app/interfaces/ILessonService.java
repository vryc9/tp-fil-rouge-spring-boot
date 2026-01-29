package com.example.catalog_service.app.interfaces;

import com.example.catalog_service.api.dtos.LessonDto;
import com.example.catalog_service.api.dtos.LessonWithThemeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ILessonService {

    /**
     * Ajoute une lecon à un theme
     * @param lessonDto LessonDto à genere
     * @return LessonDto cree
     */
    LessonWithThemeDto addLessonToTheme(int idTheme, LessonDto lessonDto);

    /**
     * Récupère-les lecons et leurs themes
     * @return Les lecons et leurs themes
     */
    Page<LessonDto> getLessonsWithThemePageable(Pageable pageable);
}
