package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "profil")
public class Profile {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idProfil")
    private Integer profileId;

    @Column(name = "pseudoUtilisateur")
    private String userNickname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "utilisateur_idUtilisateur", referencedColumnName = "idUtilisateur")
    private User user;
}
