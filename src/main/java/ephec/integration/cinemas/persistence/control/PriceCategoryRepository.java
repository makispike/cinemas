package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.PriceCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceCategoryRepository extends CrudRepository<PriceCategory, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.

    // find all the price categories linked to a location
    Iterable<PriceCategory> findByLocation_LocationId(Integer id);

    Optional<PriceCategory> findByPriceCategoryIdAndLocation_LocationId(Integer priceId, Integer locationId);
}
