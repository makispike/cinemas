package ephec.integration.cinemas.persistence.boundary;

import ephec.integration.cinemas.persistence.entity.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.
}