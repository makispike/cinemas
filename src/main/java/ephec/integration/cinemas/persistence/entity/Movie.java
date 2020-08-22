package ephec.integration.cinemas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "movieId")
@Entity
@Getter @Setter
@Table(name = "film")
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idFilm")
    private Integer movieId;

    @Column(name = "nomFilmFR", nullable = false, unique = true)
    private String movieNameFR;

    @Column(name = "nomFilmNL", nullable = false, unique = true)
    private String movieNameNL;

    @Column(name = "nomFilmEN", nullable = false, unique = true)
    private String movieNameEN;

    @Column(name = "descriptionFR")
    private String movieDescriptionFR;

    @Column(name = "descriptionNL")
    private String movieDescriptionNL;

    @Column(name = "descriptionEN")
    private String movieDescriptionEN;

    @Column(name = "urlPhotoFilm")
    private String moviePictureUrl;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "film_has_genre",
            joinColumns = { @JoinColumn(name = "film_idFilm") },
            inverseJoinColumns = { @JoinColumn(name = "genre_idGenre") }
    )
    List<Genre> genres = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "film_has_version",
            joinColumns = { @JoinColumn(name = "film_idFilm") },
            inverseJoinColumns = { @JoinColumn(name = "version_idVersion") }
    )
    List<Version> versions = new ArrayList<>();

    @OneToMany(mappedBy="movie", cascade = CascadeType.MERGE)
    @JsonManagedReference(value="movie-screening")
    private Set<Screening> screenings;
}
