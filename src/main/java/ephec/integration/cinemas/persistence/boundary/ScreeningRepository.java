package ephec.integration.cinemas.persistence.boundary;

import ephec.integration.cinemas.persistence.entity.Screening;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ScreeningRepository extends CrudRepository<Screening, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.
    Iterable<Screening> findByScreeningDateOrderByScreeningId(LocalDate date);

    Iterable<Screening> findByMovie_MovieId(Integer id);
}
