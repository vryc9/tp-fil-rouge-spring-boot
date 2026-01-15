package edu.esiea.tp_fil_rouge.infra;

import edu.esiea.tp_fil_rouge.domain.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILessonRepository extends JpaRepository<Lesson, Long> {
}
