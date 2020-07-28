package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "profil")
public class Profile {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "idProfil")
    private Integer profileId;

    @Column(name = "nomUtilisateur")
    private String userLastName;

    @Column(name = "prenomUtilisateur")
    private String userFirstName;

    @Column(name = "adresseUtilisateur")
    private String userAddress;

    @Column(name = "ageUtilisateur")
    private Integer userAge;

    @Column(name = "langueUtilisateur")
    private String userLanguage;

    @Column(name = "pseudoUtilisateur")
    private String userNickname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "utilisateur_idUtilisateur", referencedColumnName = "idUtilisateur")
    private User user;
}
