package ephec.integration.cinemas.persistence.boundary;

import ephec.integration.cinemas.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.
    Optional<User> findByUserEmail(String email);
}
