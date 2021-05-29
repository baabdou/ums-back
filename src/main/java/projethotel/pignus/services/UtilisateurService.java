package projethotel.pignus.services;

import org.springframework.stereotype.Service;
import projethotel.pignus.entities.AppUser;
import projethotel.pignus.entities.Hotel;
import projethotel.pignus.entities.Utilisateurs;

import javax.transaction.Transactional;
import java.util.List;

public interface UtilisateurService {
    Utilisateurs saveUtilisateur(String firstname, String lastname, String email, String phone, int nbreAccompagnateur, String address, String sex, Hotel hotel, AppUser appUser);
    Utilisateurs loadUserByEmail(String email);
}
