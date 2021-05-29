package projethotel.pignus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projethotel.pignus.dao.AppRoleRepository;
import projethotel.pignus.dao.AppUserRepository;
import projethotel.pignus.emailhandler.EmailSenderImpl;
import projethotel.pignus.entities.AppRole;
import projethotel.pignus.entities.AppUser;
import projethotel.pignus.entities.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private EmailSenderImpl emailSender;

    @Override
    public AppUser saveUser(String firstname, String lastname, String phone, String email, String address, String sex, String password, String confirmedPassword) {
        AppUser user = appUserRepository.findByEmail(email);
        if(user != null) throw new RuntimeException("Cet utilisateur existe déjà");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Confirmez votre mot de passe s'il vous plait");
        AppUser appUser = new AppUser();
        appUser.setFirstname(firstname);
        appUser.setLastname(lastname);
        appUser.setPhone(phone);
        appUser.setEmail(email);
        appUser.setAddress(address);
        appUser.setSex(sex);
        appUser.setActived(false);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        addRoleToUser(email, "ADMIN");

        //saveTokenAndSendConfirmationEmail(appUser);

        return appUser;
    }

    @Override
    public AppUser updateUser(Long id, String firstname, String lastname, String phone, String email, String address, String sex, String password, String confirmedPassword) {
        AppUser appUser = appUserRepository.findById(id).get();
        AppUser appUser1 = appUserRepository.findByEmail(email);
        if(appUser == null) throw new RuntimeException("Cet utilisateur n'existe pas");
        if(appUser1 != null && appUser1.getEmail() != appUser.getEmail()) throw new RuntimeException("Mis a jour impossible! Cet email est deja pris");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Confirmez votre mot de passe s'il vous plait");
        appUser.setFirstname(firstname);
        appUser.setLastname(lastname);
        appUser.setPhone(phone);
        appUser.setEmail(email);
        appUser.setAddress(address);
        appUser.setSex(sex);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        return appUser;
    }




    @Override
    public AppUser getUser(Long id) {
        return appUserRepository.findById(id).get();
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }


    @Override
    public void addRoleToUser(String email, String rolename) {
        AppUser appUser=appUserRepository.findByEmail(email);
        AppRole appRole=appRoleRepository.findByRoleName(rolename);
        appUser.getRoles().add(appRole);
    }



    @Override
    @Transactional
    public String confirm(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token);
        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expiré");
        }
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Cet email a été déjà confirmé");
        }
        try {
            AppUser appUser = confirmationToken.getAppUser();
            appUser.setActived(true);
            appUserRepository.save(appUser);
            confirmationToken.setConfirmedAt(LocalDateTime.now());
            confirmationTokenService.saveConfirmationToken(confirmationToken);
            return "successfully-activated";
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de l'activation du compte: " + e);
            return "activation-error";
        }
    }


    public void saveTokenAndSendConfirmationEmail(AppUser appUser) {
        //Sending confirmation email to user
            //Generate and save token
        int token_time_limit = 30;
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(token_time_limit),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
            //Send email
        String link = "http://localhost:8080/api/registration/confirm?token="+token;
//        String link = "http://www.toctocdoc.sn/api/registration/confirm?token="+token;
        Map<String, Object> datas = new HashMap<String, Object>();
        datas.put("name", appUser.getFirstname());
        datas.put("link", link);
        datas.put("token_time_limit", token_time_limit);
        //emailSender.sendEmail(appUser.getEmail(), buildEmail(appUser.getFirstname(), link));
        //emailSender.sendEmail(appUser.getEmail(), buildEmailVerification(appUser.getFirstname(), link, token_time_limit));
        emailSender.sendConfirmationEmail(appUser.getEmail(), "Email de confirmation", datas);
    }

}
