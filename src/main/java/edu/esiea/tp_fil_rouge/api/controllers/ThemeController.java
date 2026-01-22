package edu.esiea.tp_fil_rouge.api.controllers;

import edu.esiea.tp_fil_rouge.api.dtos.LessonDto;
import edu.esiea.tp_fil_rouge.api.dtos.LessonWithThemeDto;
import edu.esiea.tp_fil_rouge.api.dtos.ThemeDto;
import edu.esiea.tp_fil_rouge.app.interfaces.ILessonService;
import edu.esiea.tp_fil_rouge.app.interfaces.IThemeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Créer un nouveau thème",
            description = "Crée un thème avec un nom unique. Accessible uniquement aux utilisateurs avec le rôle ADMIN.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Thème créé avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ThemeDto.class))
                    ),
                    @ApiResponse(responseCode = "403", description = "Accès refusé - non autorisé")
            }
    )
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
    @Operation(
            summary = "Ajouter une leçon à un thème spécifique",
            description = "Ajoute une nouvelle leçon associée au thème identifié par son ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Leçon ajoutée avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = LessonWithThemeDto.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Thème non trouvé")
            }
    )
    @PostMapping("/{id}/lessons")
    @PreAuthorize("hasAuthority('AUTHOR')")
    public ResponseEntity<LessonWithThemeDto> addLessonToTheme(
            @PathVariable("id") int themeId,
            @RequestBody LessonDto lessonDto) {
        LessonWithThemeDto dto = lessonService.addLessonToTheme(themeId, lessonDto);
        return ResponseEntity.ok().body(dto);
    }
}
