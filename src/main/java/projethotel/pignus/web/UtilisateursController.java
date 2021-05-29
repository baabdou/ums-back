package projethotel.pignus.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projethotel.pignus.dao.UtilisateurRepository;
import projethotel.pignus.entities.AppUser;
import projethotel.pignus.entities.Hotel;
import projethotel.pignus.entities.Utilisateurs;
import projethotel.pignus.services.AccountService;
import projethotel.pignus.services.UtilisateurService;

import java.util.List;

@RestController
public class UtilisateursController {
    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("/api/utilisateur")
    public Utilisateurs register(@RequestBody Utilisateurs userForm){
        Utilisateurs utilis=utilisateurService.loadUserByEmail(userForm.getEmail());
        if (utilis!=null)
            throw new RuntimeException("This user already exist");
        Utilisateurs utilisateur=new Utilisateurs();
        utilisateur.setFirstname(userForm.getFirstname());
        utilisateur.setLastname(userForm.getLastname());
        utilisateur.setPhone(userForm.getPhone());
        utilisateur.setEmail(userForm.getEmail());
        utilisateur.setNbreAccompagnateur(userForm.getNbreAccompagnateur());
        utilisateur.setAddress(userForm.getAddress());
        utilisateur.setSex(userForm.getSex());
        utilisateur.setHotel(userForm.getHotel());
        utilisateur.setAppUser(userForm.getAppUser());
        utilisateurService.saveUtilisateur(utilisateur.getFirstname(),utilisateur.getLastname(),utilisateur.getPhone(),utilisateur.getEmail(),utilisateur.getNbreAccompagnateur(),utilisateur.getAddress()
                , utilisateur.getSex(), utilisateur.getHotel(),utilisateur.getAppUser());

        return utilisateur;
    }

    @GetMapping("/api/utilisateurs")
    public List<Utilisateurs> getUtilisateurs(){
        return  utilisateurRepository.findAll();
    }
    @GetMapping("/api/utilisateurs/{id}")
    public Utilisateurs getOneUtilisateur(@PathVariable Long id) {
        return utilisateurRepository.findById(id).get();
    }
}
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
class UtilisateurForm {
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private int nbreAccompagnateur;
    private String address;
    private String sex;
    private Hotel hotel;
    private AppUser appUser;
}
