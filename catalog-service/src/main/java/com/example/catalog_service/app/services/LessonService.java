package com.example.catalog_service.app.services;

import com.example.catalog_service.api.dtos.LessonCreateEvent;
import com.example.catalog_service.api.dtos.LessonDto;
import com.example.catalog_service.api.dtos.LessonWithThemeDto;
import com.example.catalog_service.api.mapper.LessonMapper;
import com.example.catalog_service.app.interfaces.ILessonService;
import com.example.catalog_service.domain.models.Lesson;
import com.example.catalog_service.domain.models.Theme;
import com.example.catalog_service.infra.ILessonRepository;
import com.example.catalog_service.infra.IThemeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
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

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

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
        kafkaTemplate.send("course-created", new LessonCreateEvent(lesson.getId(), lesson.getTitle()));
        return lessonMapper.toDtoWithTheme(lesson);
    }

    @Override
    public Page<LessonDto> getLessonsWithThemePageable(Pageable pageable) {
        return lessonRepository.findAll(pageable)
                .map(lessonMapper::toDto);
    }
}
