package ephec.integration.cinemas.persistence.controllers;

import ephec.integration.cinemas.persistence.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.
}
