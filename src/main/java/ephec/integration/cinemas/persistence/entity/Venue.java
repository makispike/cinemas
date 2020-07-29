package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "salle")
public class Venue {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idSalle", nullable = false, updatable = false)
    private Integer venueId;

    @Column(name = "nbSalle")
    private Integer venueNumber;

    @Column(name = "nbPlacesSalle")
    private Integer venueSeatsAmount;

    @OneToMany(mappedBy="venue", cascade = CascadeType.ALL)
    private Set<Screening> screenings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="complexe_idComplexe")
    private Location location;
}
