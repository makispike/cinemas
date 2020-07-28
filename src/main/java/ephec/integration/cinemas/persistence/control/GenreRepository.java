package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.
}
