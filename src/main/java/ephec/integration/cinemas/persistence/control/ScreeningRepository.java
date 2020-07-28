package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.Screening;
import org.springframework.data.repository.CrudRepository;

public interface ScreeningRepository extends CrudRepository<Screening, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.
}
