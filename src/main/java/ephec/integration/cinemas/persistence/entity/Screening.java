package ephec.integration.cinemas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "seance")
public class Screening {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idSeance", nullable = false, updatable = false)
    private Integer screeningId;

    @Column(name = "dateSeance")
    private LocalDate screeningDate;

    @Column(name = "heureSeance")
    private LocalTime screeningTime;

    @Column(name = "nbPlacesDisponibles")
    private Integer availableSeats;

    @ManyToOne()
    @JoinColumn(name="salle_idSalle")
    @JsonBackReference(value="venue-screening")
    private Venue venue;

    @ManyToOne()
    @JoinColumn(name="film_idFilm")
    @JsonBackReference(value="movie-screening")
    private Movie movie;

    @OneToMany(mappedBy="screening", cascade = CascadeType.ALL)
    @JsonManagedReference(value="ticket-screening")
    private Set<Ticket> tickets;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="version_idVersion", referencedColumnName = "idVersion")
    private Version version;
}
