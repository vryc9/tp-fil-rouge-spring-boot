package edu.esiea.tp_fil_rouge.api.controllers;


import edu.esiea.tp_fil_rouge.api.dtos.LessonDto;
import edu.esiea.tp_fil_rouge.app.interfaces.ILessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Récupère une page de leçons",
            description = "Retourne une page paginée des leçons avec leur thème associé.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Page de leçons récupérée avec succès"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Page<LessonDto>> getLessons(@Parameter(description = "Paramètres de pagination et tri") Pageable pageable) {

        Page<LessonDto> lessons = lessonService.getLessonsWithThemePageable(pageable);
        return ResponseEntity.ok(lessons);
    }
}
