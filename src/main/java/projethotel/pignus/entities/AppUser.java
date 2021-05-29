package projethotel.pignus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String username;
    private String phone;
    private String sex;
    private String address;
    private int nbreAccompagnateur;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean actived;
    @JsonIgnore
    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
    private Collection<Utilisateurs> utilisateurs=new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles=new ArrayList<>();
//    @ManyToMany(fetch = FetchType.LAZY)
//    private Collection<Hotel> hotels=new ArrayList<>();


}
