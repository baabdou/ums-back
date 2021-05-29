package projethotel.pignus.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @Entity
public class ConfirmationToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false
    )
    private AppUser appUser;

    public ConfirmationToken(String token,
                             LocalDateTime generatedAt,
                             LocalDateTime expiresAt,
                             AppUser appUser) {
        this.token = token;
        this.generatedAt = generatedAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }


}
