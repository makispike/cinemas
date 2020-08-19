package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
public class VenueDTO {
    @Id
    private Integer venueId;
    private Integer venueNumber;
    private Integer venueSeatsAmount;
    private List<ScreeningDTO> screenings;
    private LocationDTO location;
}
