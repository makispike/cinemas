package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.PriceCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceCategoryRepository extends CrudRepository<PriceCategory, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.
}
