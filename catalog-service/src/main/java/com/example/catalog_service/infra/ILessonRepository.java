package com.example.catalog_service.infra;

import com.example.catalog_service.domain.models.Lesson;
import org.springframework.data.domain.Page;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface ILessonRepository extends JpaRepository<Lesson, Long> {
    @Override
    @EntityGraph(attributePaths = "theme")
    @NonNull
    Page<Lesson> findAll(@NonNull Pageable pageable);

    boolean existsByTitle(String title);
}
