package ephec.integration.cinemas.persistence.control;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDTO {
    private Integer genreId;
    private String genreLabelFR;
    private String genreLabelNL;
    private String genreLabelEN;
}
