package ephec.integration.cinemas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reservationId")
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

    @Column(name = "prixTotalReservation")
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="utilisateur_idUtilisateur")
    @JsonBackReference(value="user-reservation")
    private User user;

    @OneToMany(mappedBy="reservation", cascade = CascadeType.ALL)
    @JsonManagedReference(value="ticket-reservation")
    private Set<Ticket> tickets;
}
