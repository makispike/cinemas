package ephec.integration.cinemas.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "salle")
public class Venue {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "idSalle")
    private Integer venueId;

    @Column(name = "nbSalle")
    private Integer venueNumber;

    @Column(name = "nbPlacesSalle")
    private Integer venueSeatsAmount;

    @OneToMany(mappedBy="venue")
    private Set<Screening> screenings;

    @ManyToOne
    @JoinColumn(name="complexe_idComplexe")
    private Location location;
}
