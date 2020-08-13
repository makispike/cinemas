package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idReservation", nullable = false, updatable = false)
    private Integer reservationId;

    @Column(name = "dateReservation")
    private LocalDateTime reservationDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="utilisateur_idUtilisateur")
    private User user;

    @OneToMany(mappedBy="reservation", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;
}
