package ephec.integration.cinemas.persistence.control;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDTO {
    private Integer ticketId;
    private ReservationDTO reservation;
    private ScreeningDTO screening;
    private PriceCategoryDTO priceCategory;
}
