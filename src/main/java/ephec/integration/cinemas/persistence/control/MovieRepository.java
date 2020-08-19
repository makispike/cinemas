package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.
    // find a movie by its English title
    Optional<Movie> findByMovieNameEN(String titleEN);

    // find a movie by its French title
    Optional<Movie> findByMovieNameFR(String titleFR);

    // find a movie by its Dutch title
    Optional<Movie> findByMovieNameNL(String titleNL);
}
