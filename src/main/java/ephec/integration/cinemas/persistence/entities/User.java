package ephec.integration.cinemas.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "utilisateur")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "idUtilisateur")
    private Integer userId;

    @Column(name = "emailUtilisateur")
    private String userEmail;

    @Column(name = "roleUtilisateur")
    private String userRole;

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @OneToMany(mappedBy="user")
    private Set<Reservation> reservations;

}
