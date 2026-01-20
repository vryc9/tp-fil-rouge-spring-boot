package edu.esiea.tp_fil_rouge.api.controllers;

import edu.esiea.tp_fil_rouge.api.dtos.LessonWithThemeDto;
import org.springframework.web.bind.annotation.*;

import edu.esiea.tp_fil_rouge.api.dtos.LessonDto;
import edu.esiea.tp_fil_rouge.api.dtos.ThemeDto;
import edu.esiea.tp_fil_rouge.app.interfaces.ILessonService;
import edu.esiea.tp_fil_rouge.app.interfaces.IThemeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {
    @Autowired
    private IThemeService themeService;

    @Autowired
    private ILessonService lessonService;

    /**
     * POST /api/themes
     * Créer un nouveau thème avec un nom unique.
     */
    @PostMapping
    public ResponseEntity<ThemeDto> createTheme(@RequestBody ThemeDto themeDto) {
        // Logique pour créer un thème
        ThemeDto createdTheme = themeService.createTheme(themeDto);
        return ResponseEntity.status(201).body(createdTheme);
    }


    /**
     * POST /api/themes/{id}/lessons
     * Ajouter une leçon à un thème spécifique.
     */
    @PostMapping("/{id}/lessons")
    public ResponseEntity<LessonWithThemeDto> addLessonToTheme(
            @PathVariable("id") int themeId, 
            @RequestBody LessonDto lessonDto) {
        LessonWithThemeDto dto =  lessonService.addLessonToTheme(themeId, lessonDto);
        return ResponseEntity.ok().body(dto);
    }
}
