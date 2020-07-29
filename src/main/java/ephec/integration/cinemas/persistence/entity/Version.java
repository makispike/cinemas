package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "version")
public class Version {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idVersion")
    private Integer versionId;

    @Column(name = "libelleVersion")
    private String versionLabel;

    @ManyToMany(mappedBy = "versions", cascade = CascadeType.ALL)
    private Set<Movie> movies = new HashSet<>();
}
