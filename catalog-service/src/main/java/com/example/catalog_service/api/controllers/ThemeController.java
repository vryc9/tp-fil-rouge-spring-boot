package com.example.catalog_service.api.controllers;

import com.example.catalog_service.api.dtos.LessonDto;
import com.example.catalog_service.api.dtos.LessonWithThemeDto;
import com.example.catalog_service.api.dtos.ThemeDto;
import com.example.catalog_service.app.interfaces.ILessonService;
import com.example.catalog_service.app.interfaces.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('AUTHOR')")
    public ResponseEntity<LessonWithThemeDto> addLessonToTheme(
            @PathVariable("id") int themeId,
            @RequestBody LessonDto lessonDto) {
        LessonWithThemeDto dto = lessonService.addLessonToTheme(themeId, lessonDto);
        return ResponseEntity.ok().body(dto);
    }
}
