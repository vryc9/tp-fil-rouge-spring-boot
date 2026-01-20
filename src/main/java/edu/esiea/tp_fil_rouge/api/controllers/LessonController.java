package edu.esiea.tp_fil_rouge.api.controllers;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.esiea.tp_fil_rouge.api.dtos.LessonDto;
import edu.esiea.tp_fil_rouge.app.interfaces.ILessonService;
import edu.esiea.tp_fil_rouge.app.services.LessonService;

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
