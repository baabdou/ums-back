package projethotel.pignus.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity @Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Utilisateurs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String phone;
    private String sex;
    private String address;
    private int nbreAccompagnateur;
    @ManyToOne
    private AppUser appUser;
    @ManyToOne
    private Hotel hotel;


}
