package ephec.integration.cinemas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idTicket", nullable = false, updatable = false)
    private Integer ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reservation_idReservation")
    @JsonBackReference(value="ticket-reservation")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="seance_idSeance")
    @JsonBackReference(value="ticket-screening")
    private Screening screening;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categorie_idCategorie")
    @JsonBackReference(value="price-ticket")
    private PriceCategory priceCategory;
}
