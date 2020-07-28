package ephec.integration.cinemas.persistence.entities;

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
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "idReservation")
    private Integer reservationId;

    @Column(name = "dateReservation")
    private LocalDateTime reservationDateTime;

    @ManyToOne
    @JoinColumn(name="utilisateur_idUtilisateur")
    private User user;

    @OneToMany(mappedBy="reservation")
    private Set<Ticket> tickets;
}
