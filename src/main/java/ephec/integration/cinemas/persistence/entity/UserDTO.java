package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    @Id
    private Integer userId;
    private String userEmail;
    private String userLastName;
    private String userFirstName;
    private String userAddress;
    private String userContactEmail;
    private List<ReservationDTO> reservations;
}
