package edu.esiea.tp_fil_rouge.app.services;

import edu.esiea.tp_fil_rouge.app.interfaces.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.esiea.tp_fil_rouge.api.dtos.ThemeDto;
import edu.esiea.tp_fil_rouge.domain.models.Theme;
import edu.esiea.tp_fil_rouge.infra.IThemeRepository;

import java.util.List;


@Service
public class ThemeService implements IThemeService {

    @Autowired
    private IThemeRepository themeRepository;

    /**
     * Récupère les thèmes de manière paginée.
     * Le Pageable contient les infos : page, size, et sort.
     */
    @Transactional(readOnly = true)
    public Page<ThemeDto> getAllThemes(Pageable pageable) {
        // 1. Récupère la page d'entités depuis le repository
        Page<Theme> themePage = themeRepository.findAll(pageable);

        // 2. Utilise la méthode .map() de l'objet Page pour transformer les entités en Records
        return themePage.map(theme -> new ThemeDto(theme.getId(), theme.getName()));
    }

    @Transactional
    public ThemeDto createTheme(ThemeDto themeDto) {
        if (themeRepository.existsByName(themeDto.name())) {
            throw new RuntimeException("Un thème avec ce nom existe déjà.");
        }

        Theme theme = new Theme();
        theme.setName(themeDto.name()); 

        Theme savedTheme = themeRepository.save(theme);
        return new ThemeDto(savedTheme.getId(), savedTheme.getName());
    }
}
