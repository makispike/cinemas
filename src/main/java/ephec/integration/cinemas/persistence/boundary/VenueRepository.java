package ephec.integration.cinemas.persistence.boundary;

import ephec.integration.cinemas.persistence.entity.Venue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VenueRepository extends CrudRepository<Venue, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.
    Iterable<Venue> findByLocation_LocationId(Integer locationId);

    Optional<Venue> findByVenueIdAndLocation_LocationId(Integer id, Integer locationId);
}
