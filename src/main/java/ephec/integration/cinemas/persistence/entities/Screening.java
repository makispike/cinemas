package ephec.integration.cinemas.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "seance")
public class Screening {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "idSeance")
    private Integer screeningId;

    @Column(name = "dateSeance")
    private Integer screeningDate;

    @Column(name = "heureSeance")
    private Integer screeningTime;

    @Column(name = "nbPlacesDisponibles")
    private Integer availableSeats;

    @ManyToOne
    @JoinColumn(name="salle_idSalle")
    private Venue venue;

    @ManyToOne
    @JoinColumn(name="film_idFilm")
    private Movie movie;

    @OneToMany(mappedBy="screening")
    private Set<Ticket> tickets;
}
