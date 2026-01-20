package edu.esiea.tp_fil_rouge.app.interfaces;

import edu.esiea.tp_fil_rouge.api.dtos.ThemeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IThemeService {
    ThemeDto createTheme(ThemeDto themeDto);

    Page<ThemeDto> getAllThemes(Pageable pageable);
}
