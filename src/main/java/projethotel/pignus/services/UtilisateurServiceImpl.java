package projethotel.pignus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projethotel.pignus.dao.UtilisateurRepository;
import projethotel.pignus.entities.AppUser;
import projethotel.pignus.entities.Hotel;
import projethotel.pignus.entities.Utilisateurs;

import java.util.List;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService{
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateurs saveUtilisateur(String firstname, String lastname, String email, String phone, int nbreAccompagnateur, String address, String sex,Hotel hotel, AppUser appUser) {
        Utilisateurs user = utilisateurRepository.findByEmail(email);
        if(user != null) throw new RuntimeException("Cet utilisateur existe déjà");
        Utilisateurs utilisateurs = new Utilisateurs();
        utilisateurs.setFirstname(firstname);
        utilisateurs.setLastname(lastname);
        utilisateurs.setPhone(phone);
        utilisateurs.setNbreAccompagnateur(nbreAccompagnateur);
        utilisateurs.setEmail(email);
        utilisateurs.setAddress(address);
        utilisateurs.setSex(sex);
        utilisateurs.setHotel(hotel);
        utilisateurs.setAppUser(appUser);
        utilisateurRepository.save(utilisateurs);

        return utilisateurs;
    }

    @Override
    public Utilisateurs loadUserByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }


}
