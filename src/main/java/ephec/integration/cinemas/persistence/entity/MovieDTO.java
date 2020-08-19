package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
public class MovieDTO {
    @Id
    private Integer movieId;
    private String movieNameFR;
    private String movieNameNL;
    private String movieNameEN;
    private String movieDescriptionFR;
    private String movieDescriptionNL;
    private String movieDescriptionEN;
    private String moviePictureUrl;
    private List<GenreDTO> genres;
    private List<VersionDTO> versions;
    private List<ScreeningDTO> screenings;
}
