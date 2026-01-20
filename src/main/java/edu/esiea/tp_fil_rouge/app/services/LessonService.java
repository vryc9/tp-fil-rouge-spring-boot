package edu.esiea.tp_fil_rouge.app.services;

import edu.esiea.tp_fil_rouge.api.dtos.LessonDto;
import edu.esiea.tp_fil_rouge.api.dtos.LessonWithThemeDto;
import edu.esiea.tp_fil_rouge.api.mapper.LessonMapper;
import edu.esiea.tp_fil_rouge.app.interfaces.ILessonService;
import edu.esiea.tp_fil_rouge.domain.models.Lesson;
import edu.esiea.tp_fil_rouge.domain.models.Theme;
import edu.esiea.tp_fil_rouge.infra.ILessonRepository;
import edu.esiea.tp_fil_rouge.infra.IThemeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LessonService implements ILessonService {

    @Autowired
    private ILessonRepository lessonRepository;

    @Autowired
    private IThemeRepository themeRepository;

    @Autowired
    private LessonMapper lessonMapper;

    @Override
    public LessonWithThemeDto addLessonToTheme(int idTheme , LessonDto lessonDto) {

        if (lessonDto.title() == null || lessonDto.title().trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (lessonRepository.existsByTitle(lessonDto.title())) {
            throw new RuntimeException("Une leçon avec ce titre existe déjà.");
        }
        if (idTheme <= 0) {
            throw new IllegalArgumentException("Theme id cannot be empty");
        }

         Theme theme = themeRepository.findById((long) idTheme)
                .orElseThrow(() -> new IllegalArgumentException("Thème introuvable avec l'id : " + idTheme));

        Lesson lesson = lessonMapper.toEntity(lessonDto);
        lesson.setTheme(theme);
        lesson = lessonRepository.save(lesson);
        return lessonMapper.toDtoWithTheme(lesson);
    }

    @Override
    public Page<LessonDto> getLessonsWithThemePageable(Pageable pageable) {
        return lessonRepository.findAll(pageable)
                .map(lessonMapper::toDto);
    }
}
