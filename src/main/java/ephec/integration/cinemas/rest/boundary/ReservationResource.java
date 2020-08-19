package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.control.ReservationRepository;
import ephec.integration.cinemas.persistence.control.ScreeningRepository;
import ephec.integration.cinemas.persistence.control.TicketRepository;
import ephec.integration.cinemas.persistence.control.*;
import ephec.integration.cinemas.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

// Documentation about these endpoints can be found at {this application's URL}/swagger-ui.html
@RestController
@RequestMapping(path = "/reservation")
public class ReservationResource {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ScreeningRepository screeningRepository;
    @Autowired
    private DTOUtils dtoUtils;

    @CrossOrigin
    @GetMapping(path = "/all")
    @RolesAllowed({"cinemas-admin", "admin"})
    public List<ReservationDTO> getAllReservations() {
        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        for (Reservation reservation : reservationRepository.findAll()) {
            reservationDTOs.add(createReservationDTO(reservation));
        }
        return reservationDTOs;
    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    @RolesAllowed({"cinemas-admin", "admin", "cinemas-user", "user"})
    public Reservation getReservationById(@PathVariable Integer id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @CrossOrigin
    @GetMapping(path = "/user/{userId}")
    @RolesAllowed({"cinemas-admin", "admin", "cinemas-user", "user"})
    public Iterable<Reservation> getAllReservationsForUser(@PathVariable Integer userId) {
        return reservationRepository.findByUser_UserId(userId);
    }

    @CrossOrigin
    @PostMapping(path = "/create")
    @RolesAllowed({"cinemas-admin", "admin", "cinemas-user", "user"})
    public ReservationDTO createReservation(@DTO(TicketDTO[].class) Ticket[] tickets) throws Exception {
        List<Ticket> createdTicketsForScreening = ticketRepository.findByScreening_ScreeningId(tickets[0].getScreening().getScreeningId());
        Screening screeningFromDB = screeningRepository.findById(tickets[0].getScreening().getScreeningId()).orElse(null);

        // Check whether we can save the amount of tickets
        if (createdTicketsForScreening != null
                && screeningFromDB != null
                && (createdTicketsForScreening.size() + tickets.length > screeningFromDB.getAvailableSeats())) {
            throw new Exception("There aren't enough available seats!");
        }

        // Create a new reservation first, we need the reservation to exist when creating the tickets
        // JPA/Hibernate errors about detached entities otherwise
        Reservation reservation = reservationRepository.save(tickets[0].getReservation());
        for (Ticket ticket : tickets) {
            ticket.setReservation(reservation);
            ticketRepository.save(ticket);
        }

        return createReservationDTO(reservationRepository.findById(reservation.getReservationId()).orElse(null));
    }

    @CrossOrigin
    @PutMapping(path = "/update")
    @RolesAllowed({"cinemas-admin", "admin"})
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

    private ReservationDTO createReservationDTO(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        ReservationDTO reservationDTO = new ReservationDTO();
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setUser(dtoUtils.getUserDTO(reservation.getUser()));

        for (Ticket ticket : reservation.getTickets()) {
            TicketDTO newTicketDTO = dtoUtils.getTicketDTO(ticket);
            PriceCategoryDTO priceCategoryDTO = dtoUtils.getPriceCategoryDTO(ticket.getPriceCategory());
            ScreeningDTO screeningDTO = dtoUtils.getScreeningsDTO(ticket.getScreening());

            newTicketDTO.setPriceCategory(priceCategoryDTO);
            newTicketDTO.setScreening(screeningDTO);
            ticketDTOs.add(newTicketDTO);
        }

        reservationDTO.setTickets(ticketDTOs);
        return reservationDTO;
    }
}
