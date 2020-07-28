package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    // By default, there is only a findAll() method, in case there are more specific queries needed, write them here.
}
