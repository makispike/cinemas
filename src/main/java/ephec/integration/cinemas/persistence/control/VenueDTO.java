package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.Screening;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class VenueDTO {
    private Integer venueId;
    private Integer venueNumber;
    private Integer venueSeatsAmount;
    private Set<Screening> screenings;
    private LocationDTO location;

}
