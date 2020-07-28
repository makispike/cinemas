package ephec.integration.cinemas.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "idTicket")
    private Integer ticketId;

    @ManyToOne
    @JoinColumn(name="reservation_idReservation")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name="seance_idSeance")
    private Screening screening;

    @ManyToOne
    @JoinColumn(name="categorie_idCategorie")
    private PriceCategory priceCategory;
}
