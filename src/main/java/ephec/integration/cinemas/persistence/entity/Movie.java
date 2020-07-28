package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "film")
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "idFilm")
    private Integer movieId;

    @Column(name = "nomFilmFR")
    private String movieNameFR;

    @Column(name = "nomFilmNL")
    private String movieNameNL;

    @Column(name = "nomFilmEN")
    private String movieNameEN;

    @Column(name = "descriptionFR")
    private String movieDescriptionFR;

    @Column(name = "descriptionNL")
    private String movieDescriptionNL;

    @Column(name = "descriptionEN")
    private String movieDescriptionEN;

    @Column(name = "urlPhotoFilm")
    private String moviePictureUrl;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "film_has_genre",
            joinColumns = { @JoinColumn(name = "film_idFilm") },
            inverseJoinColumns = { @JoinColumn(name = "genre_idGenre") }
    )
    Set<Genre> genres = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "film_has_version",
            joinColumns = { @JoinColumn(name = "film_idFilm") },
            inverseJoinColumns = { @JoinColumn(name = "version_idVersion") }
    )
    Set<Version> versions = new HashSet<>();

    @OneToMany(mappedBy="movie", cascade = CascadeType.ALL)
    private Set<Screening> screenings;
}
