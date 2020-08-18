package ephec.integration.cinemas.persistence.control;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReservationDTO {
    @Id
    private Integer reservationId;
    private LocalDateTime reservationDateTime;
    private BigDecimal totalPrice;
    private UserDTO user;
    private List<TicketDTO> tickets;
}
