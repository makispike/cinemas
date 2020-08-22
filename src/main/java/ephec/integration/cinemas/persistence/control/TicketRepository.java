package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.

    List<Ticket> findByScreening_ScreeningId(Integer id);

    List<Ticket> findByReservation_ReservationId(Integer id);
}
