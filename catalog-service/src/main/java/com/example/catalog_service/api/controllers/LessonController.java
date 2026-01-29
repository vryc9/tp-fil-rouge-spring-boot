package com.example.catalog_service.api.controllers;
import com.example.catalog_service.api.dtos.LessonDto;
import com.example.catalog_service.app.interfaces.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private ILessonService lessonService;


    /**
     * POST /api/themes/{id}/lessons
     * Ajouter une leçon à un thème spécifique.
     */
    @GetMapping
    public ResponseEntity<Page<LessonDto>> getLessons(Pageable pageable) {

        Page<LessonDto> lessons = lessonService.getLessonsWithThemePageable(pageable);
        return ResponseEntity.ok(lessons);
    }
}
