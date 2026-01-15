package edu.esiea.tp_fil_rouge.infra;

import edu.esiea.tp_fil_rouge.domain.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IThemeRepository extends JpaRepository<Theme, Long> {
}
