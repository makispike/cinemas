package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idGenre", nullable = false, updatable = false)
    private Integer genreId;

    @Column(name = "libelleGenreFR")
    private Integer genreLabelFR;

    @Column(name = "libelleGenreNL")
    private Integer genreLabelNL;

    @Column(name = "libelleGenreEN")
    private Integer genreLabelEN;

    @ManyToMany(mappedBy = "genres", cascade = CascadeType.ALL)
    private Set<Movie> movies = new HashSet<>();
}
