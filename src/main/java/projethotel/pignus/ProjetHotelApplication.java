package projethotel.pignus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import projethotel.pignus.entities.AppRole;
import projethotel.pignus.entities.AppUser;
import projethotel.pignus.services.AccountService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import projethotel.pignus.services.UtilisateurService;


@SpringBootApplication
public class ProjetHotelApplication {
    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;


    public static void main(String[] args) {
        SpringApplication.run(ProjetHotelApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService, UtilisateurService utilisateurService) {
        repositoryRestConfiguration.exposeIdsFor(AppUser.class);
        return args -> {
            //accountService.saveRole(new AppRole(null,"USER"));
            //accountService.saveRole(new AppRole(null,"ADMIN"));
            //accountService.saveUser("Ousseynou","Gueye","776406502","admin","Bambilor", "M","admin","admin");
            //accountService.saveUser("Ousseynou","Gueye","776406520","administrator","Bambilor", "M","admin","admin");
            //utilisateurService.saveUtilisateur("Ousseynou","Gueye","admin","776406502","5", "Bambilor","admin","admin");
            //accountService.addRoleToUser("administrator","ADMIN");
            //accountService.addRoleToUser("admin","USER");
        };
    }
}
