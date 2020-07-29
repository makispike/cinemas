package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.Venue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends CrudRepository<Venue, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.
}