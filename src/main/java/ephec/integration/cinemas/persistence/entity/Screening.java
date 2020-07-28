package ephec.integration.cinemas.persistence.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Venue venue;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @OneToMany(mappedBy="screening", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;
}
