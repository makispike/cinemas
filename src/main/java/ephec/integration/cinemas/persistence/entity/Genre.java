package ephec.integration.cinemas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "genreId")
@Entity
@Getter @Setter
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idGenre", nullable = false, updatable = false)
    private Integer genreId;

    @Column(name = "libelleGenreFR")
    private String genreLabelFR;

    @Column(name = "libelleGenreNL")
    private String genreLabelNL;

    @Column(name = "libelleGenreEN")
    private String genreLabelEN;

    @ManyToMany(mappedBy = "genres", cascade = CascadeType.ALL)
    private Set<Movie> movies = new HashSet<>();
}
