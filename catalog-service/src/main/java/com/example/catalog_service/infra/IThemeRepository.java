package com.example.catalog_service.infra;

import com.example.catalog_service.domain.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IThemeRepository extends JpaRepository<Theme, Long> {
    boolean existsByName(String name);
}
