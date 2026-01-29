package com.example.catalog_service.app.interfaces;

import com.example.catalog_service.api.dtos.ThemeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IThemeService {
    ThemeDto createTheme(ThemeDto themeDto);

    Page<ThemeDto> getAllThemes(Pageable pageable);
}
