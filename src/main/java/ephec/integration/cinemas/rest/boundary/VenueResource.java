package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.control.VenueRepository;
import ephec.integration.cinemas.persistence.entity.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

// Documentation about these endpoints can be found at {this application's URL}/swagger-ui.html
@RestController
@RequestMapping(path = "/venue")
public class VenueResource {
    @Autowired
    private VenueRepository venueRepository;

    @CrossOrigin
    @GetMapping(path = "/all")
    public Iterable<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    public Venue getVenueById(@PathVariable Integer id) {
        return venueRepository.findById(id).orElse(null);
    }

    @CrossOrigin
    @PutMapping(path = "/update")
    @RolesAllowed({"cinemas-admin", "admin"})
    public Venue updateVenue(@RequestBody Venue venueToCreateOrUpdate) {
        return venueRepository.findById(venueToCreateOrUpdate.getVenueId())
                .map(venue -> {
                    venue.setVenueSeatsAmount(venueToCreateOrUpdate.getVenueSeatsAmount());
                    return venueRepository.save(venue);
                })
                .orElseGet(() -> {
                    return venueRepository.save(venueToCreateOrUpdate);
                });
    }

    @CrossOrigin
    @PostMapping(path = "/new")
    public Venue createVenue(@RequestBody Venue venue) {
        return venueRepository.save(venue);
    }
}
