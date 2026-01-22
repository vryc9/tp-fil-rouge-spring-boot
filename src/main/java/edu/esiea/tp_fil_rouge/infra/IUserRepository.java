package edu.esiea.tp_fil_rouge.infra;

import edu.esiea.tp_fil_rouge.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}