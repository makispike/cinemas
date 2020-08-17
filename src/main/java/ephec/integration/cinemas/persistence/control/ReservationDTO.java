package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReservationDTO {
    private Integer reservationId;
    private LocalDateTime reservationDateTime;
    private User user;
    private List<TicketDTO> tickets;
}
