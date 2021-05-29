package projethotel.pignus.services;
import projethotel.pignus.entities.AppRole;
import projethotel.pignus.entities.AppUser;

import java.util.List;

public interface AccountService
{
    AppUser saveUser(String firstname, String lastname, String phone, String email, String address, String sex, String password, String confirmedPassword);
    AppUser updateUser(Long id, String firstname,String lastname,String phone,String email,String address, String sex, String password, String confirmedPassword);
    AppUser getUser(Long id);
    AppRole saveRole(AppRole role);
    AppUser loadUserByEmail(String email);
    void addRoleToUser(String email,String rolename);
    String confirm(String token);

}
