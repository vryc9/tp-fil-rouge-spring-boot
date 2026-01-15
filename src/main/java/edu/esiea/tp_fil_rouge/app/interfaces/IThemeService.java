package edu.esiea.tp_fil_rouge.app.interfaces;

import edu.esiea.tp_fil_rouge.api.dtos.ThemeDto;

import java.util.List;

public interface IThemeService {
    ThemeDto createTheme(ThemeDto themeDto);

    List<ThemeDto> getAllThemes();
}
