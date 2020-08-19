package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class TicketDTO {
    @Id
    private Integer ticketId;
    private ReservationDTO reservation;
    private ScreeningDTO screening;
    private PriceCategoryDTO priceCategory;
}
