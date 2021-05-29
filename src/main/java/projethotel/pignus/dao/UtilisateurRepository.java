package projethotel.pignus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import projethotel.pignus.entities.AppUser;
import projethotel.pignus.entities.Utilisateurs;

public interface UtilisateurRepository extends JpaRepository<Utilisateurs,Long> {
    Utilisateurs findByEmail(String email);

}
