package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.
}
