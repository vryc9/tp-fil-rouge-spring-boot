package edu.esiea.tp_fil_rouge.app.interfaces;

import edu.esiea.tp_fil_rouge.api.dtos.LessonDto;

public interface ILessonService {
    LessonDto addLessonToTheme(LessonDto lessonDto);
}
