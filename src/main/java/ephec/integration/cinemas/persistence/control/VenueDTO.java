package ephec.integration.cinemas.persistence.control;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VenueDTO {
    private Integer venueId;
    private Integer venueNumber;
    private Integer venueSeatsAmount;
    private List<ScreeningDTO> screenings;
    private LocationDTO location;
}
