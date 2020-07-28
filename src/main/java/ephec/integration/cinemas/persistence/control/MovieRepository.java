package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.
}