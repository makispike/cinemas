package ephec.integration.cinemas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "nomUtilisateur")
    private String userLastName;

    @Column(name = "prenomUtilisateur")
    private String userFirstName;

    @Column(name = "adresseUtilisateur")
    private String userAddress;

    @Column(name = "ageUtilisateur")
    private Integer userAge;

    @Column(name = "emailContactUtilisateur")
    private String userContactEmail;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @JsonManagedReference(value="user-reservation")
    private Set<Reservation> reservations;

}
