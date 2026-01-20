package edu.esiea.tp_fil_rouge.app.services;

import edu.esiea.tp_fil_rouge.api.dtos.ThemeDto;
import edu.esiea.tp_fil_rouge.app.interfaces.IThemeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService implements IThemeService {
    @Override
    public ThemeDto createTheme(ThemeDto themeDto) {
        return null;
    }

    @Override
    public List<ThemeDto> getAllThemes() {
        return List.of();
    }
}
