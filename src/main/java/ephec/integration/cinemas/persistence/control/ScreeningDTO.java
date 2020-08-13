package ephec.integration.cinemas.persistence.control;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ScreeningDTO {
    private Integer screeningId;
    private LocalDate screeningDate;
    private LocalTime screeningTime;
    private Integer availableSeats;
    private VenueDTO venue;
    private List<MovieDTO> movies;
}
