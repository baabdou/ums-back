package projethotel.pignus.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projethotel.pignus.dao.AppUserRepository;
import projethotel.pignus.dao.UtilisateurRepository;
import projethotel.pignus.entities.AppUser;
import projethotel.pignus.entities.Hotel;
import projethotel.pignus.entities.Utilisateurs;
import projethotel.pignus.services.AccountService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    //Create Patient
    @PostMapping("/api/register")
    public AppUser register(@RequestBody UserForm userForm){
        if (!userForm.getPassword().equals(userForm.getConfirmedPassword()))
            throw new RuntimeException("You must Confirm Password");
        AppUser user=accountService.loadUserByEmail(userForm.getEmail());
        if (user!=null)
            throw new RuntimeException("This user already exist");
        AppUser appUser=new AppUser();
        appUser.setFirstname(userForm.getFirstname());
        appUser.setLastname(userForm.getLastname());
        appUser.setPhone(userForm.getPhone());
        appUser.setEmail(userForm.getEmail());
        appUser.setAddress(userForm.getAddress());
        appUser.setSex(userForm.getSex());
        appUser.setPassword(userForm.getPassword());
        accountService.saveUser(appUser.getFirstname(),appUser.getLastname(),appUser.getPhone(),appUser.getEmail(),appUser.getAddress()
        , appUser.getSex(), appUser.getPassword(), appUser.getPassword());

        return appUser;
    }

    @PutMapping("/api/update")
    public AppUser updatePatient(@RequestParam("id") Long id, @RequestBody UserForm userForm) {
        return accountService.updateUser(
                id,
                userForm.getFirstname(),
                userForm.getLastname(),
                userForm.getPhone(),
                userForm.getEmail(),
                userForm.getAddress(),
                userForm.getSex(),
                userForm.getPassword(),
                userForm.getConfirmedPassword()
        );
    }


    //Recupere  Users Details
    @GetMapping("/api/userDetails/{id}")
    public AppUser getUserDetails(@PathVariable("id") Long id){
        return accountService.getUser(id);
    }


}

@Data @NoArgsConstructor @AllArgsConstructor @ToString
class UserForm {
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String address;
    private String sex;
    private List<Hotel> hotels;
    private String password;
    private String confirmedPassword;

}
