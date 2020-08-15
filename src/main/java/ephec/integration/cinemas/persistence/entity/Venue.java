package ephec.integration.cinemas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference(value="venue-screening")
    private Set<Screening> screenings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="complexe_idComplexe")
    @JsonBackReference(value="venue-location")
    private Location location;
}
