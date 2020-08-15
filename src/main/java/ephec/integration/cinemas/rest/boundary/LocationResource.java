package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.boundary.LocationRepository;
import ephec.integration.cinemas.persistence.boundary.PriceCategoryRepository;
import ephec.integration.cinemas.persistence.boundary.VenueRepository;
import ephec.integration.cinemas.persistence.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/location")
public class LocationResource {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private PriceCategoryRepository priceCategoryRepository;
    @Autowired
    private VenueRepository venueRepository;

    @CrossOrigin
    @GetMapping(path = "/all")
    public Iterable<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    public Location getLocationById(@PathVariable Integer id) {
        return locationRepository.findById(id).orElse(null);
    }

    @CrossOrigin
    @GetMapping(path = "/prices/{locationId}")
    public Location getAllPricesForLocation(@PathVariable String locationId) {
        return locationRepository.findById(Integer.valueOf(locationId)).orElse(null);
    }

    @CrossOrigin
    @PutMapping(path = "/update")
    public Location registerOrUpdateLocation(@RequestBody Location locationToCreateOrUpdate) {
        return locationRepository.findById(locationToCreateOrUpdate.getLocationId())
                .map(location -> {
                    location.setLocationName(locationToCreateOrUpdate.getLocationName());
                    location.setLocationAddress(locationToCreateOrUpdate.getLocationAddress());
                    location.setLocationDescriptionEN(locationToCreateOrUpdate.getLocationDescriptionEN());
                    location.setLocationDescriptionFR(locationToCreateOrUpdate.getLocationDescriptionFR());
                    location.setLocationDescriptionNL(locationToCreateOrUpdate.getLocationDescriptionNL());
                    location.setLocationPhotoUrl(locationToCreateOrUpdate.getLocationPhotoUrl());
                    if (locationToCreateOrUpdate.getVenues() != null) {
                        location.setVenues(locationToCreateOrUpdate.getVenues());
                    }
                    return locationRepository.save(location);
                })
                .orElseGet(() -> {
                    return locationRepository.save(locationToCreateOrUpdate);
                });
    }

    @CrossOrigin
    @PostMapping(path = "/new")
    public Location newLocation(@RequestBody Location location) {
        return locationRepository.save(location);
    }

}
