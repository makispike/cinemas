package ephec.integration.cinemas.persistence.entity;

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
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Screening screening;

    @ManyToOne(fetch = FetchType.LAZY)
    private PriceCategory priceCategory;
}
