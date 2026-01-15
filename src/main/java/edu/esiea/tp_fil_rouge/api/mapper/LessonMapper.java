package edu.esiea.tp_fil_rouge.api.mapper;
import edu.esiea.tp_fil_rouge.api.dtos.LessonDto;
import edu.esiea.tp_fil_rouge.domain.models.Lesson;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    LessonDto toDto(Lesson lesson);
    Lesson toEntity(LessonDto dto);
}
