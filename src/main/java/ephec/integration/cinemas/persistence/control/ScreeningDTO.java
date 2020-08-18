package ephec.integration.cinemas.persistence.control;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ScreeningDTO {
    @Id
    private Integer screeningId;
    private LocalDate screeningDate;
    private LocalTime screeningTime;
    private Integer availableSeats;
    private VenueDTO venue;
    private MovieDTO movie;
    private VersionDTO version;
}
