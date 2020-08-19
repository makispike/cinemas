package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {
    // find a genre by its English label
    Optional<Genre> findByGenreLabelEN(String labelEN);

    // find a genre by its French label
    Optional<Genre> findByGenreLabelFR(String labelFR);

    // find a genre by its Dutch label
    Optional<Genre> findByGenreLabelNL(String labelNL);
}
