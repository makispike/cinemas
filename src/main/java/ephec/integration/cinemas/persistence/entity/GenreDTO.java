package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class GenreDTO {
    @Id
    private Integer genreId;
    private String genreLabelFR;
    private String genreLabelNL;
    private String genreLabelEN;
}
