package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "utilisateur")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idUtilisateur", nullable = false, updatable = false)
    private Integer userId;

    @Column(name = "emailUtilisateur")
    private String userEmail;

    @Column(name = "roleUtilisateur")
    private String userRole;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Profile profile;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

}
