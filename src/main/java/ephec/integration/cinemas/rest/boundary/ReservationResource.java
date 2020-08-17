package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.boundary.*;
import ephec.integration.cinemas.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Documentation about these endpoints can be found at {this application's URL}/swagger-ui.html
@RestController
@RequestMapping(path = "/reservation")
public class ReservationResource {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @GetMapping(path = "/all")
    public Iterable<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    public Reservation getReservationById(@PathVariable Integer id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @CrossOrigin
    @GetMapping(path = "/user/{userId}")
    public Iterable<Reservation> getAllReservationsForUser(@PathVariable Integer userId) {
        return reservationRepository.findByUser_UserId(userId);
    }

    @CrossOrigin
    @PutMapping(path = "/update")
    public Reservation registerOrUpdateReservation(@RequestBody Reservation reservationToCreateOrUpdate) {
        return reservationRepository.findById(reservationToCreateOrUpdate.getReservationId())
                .map(reservation -> {
                    reservation.setUser(reservationToCreateOrUpdate.getUser());
                    for (Ticket ticket : reservationToCreateOrUpdate.getTickets()) {
                        ticketRepository.findById(ticket.getTicketId())
                                .map(foundTicket -> {
                                    foundTicket.setScreening(ticket.getScreening());
                                    foundTicket.setPriceCategory(ticket.getPriceCategory());
                                    return ticketRepository.save(foundTicket);
                                })
                                .orElseGet(() -> ticketRepository.save(ticket));
                    }
                    return reservationRepository.save(reservation);
                })
                .orElseGet(() -> {
                    return reservationRepository.save(reservationToCreateOrUpdate);
                });
    }
}
