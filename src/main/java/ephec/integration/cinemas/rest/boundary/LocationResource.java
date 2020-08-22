package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.control.DTOUtils;
import ephec.integration.cinemas.persistence.control.LocationRepository;
import ephec.integration.cinemas.persistence.control.PriceCategoryRepository;
import ephec.integration.cinemas.persistence.control.VenueRepository;
import ephec.integration.cinemas.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

// Documentation about these endpoints can be found at {this application's URL}/swagger-ui.html
@RestController
@RequestMapping(path = "/location")
public class LocationResource {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private PriceCategoryRepository priceCategoryRepository;
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private DTOUtils dtoUtils;

    @CrossOrigin
    @GetMapping(path = "/all")
    public List<LocationDTO> getAllLocations() {
        List<LocationDTO> locationDTOS = new ArrayList<>() ;
        for (Location location : locationRepository.findAll()) {
            locationDTOS.add(createLocationDTO(location));
        }
        return locationDTOS;
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
    @RolesAllowed({"cinemas-admin", "admin"})
    public Location registerOrUpdateLocation(@RequestBody Location locationToCreateOrUpdate) {
        return locationRepository.findById(locationToCreateOrUpdate.getLocationId())
                .map(location -> {
                    location.setLocationName(locationToCreateOrUpdate.getLocationName());
                    location.setLocationAddress(locationToCreateOrUpdate.getLocationAddress());
                    location.setLocationDescriptionEN(locationToCreateOrUpdate.getLocationDescriptionEN());
                    location.setLocationDescriptionFR(locationToCreateOrUpdate.getLocationDescriptionFR());
                    location.setLocationDescriptionNL(locationToCreateOrUpdate.getLocationDescriptionNL());
                    location.setLocationPhotoUrl(locationToCreateOrUpdate.getLocationPhotoUrl());
                    if (locationToCreateOrUpdate.getVenues() != null) { // we need to take into account that a venue might already exist or we're going to have issues persisting
                        for (Venue venue : locationToCreateOrUpdate.getVenues()) {
                            venueRepository.findByVenueIdAndLocation_LocationId(venue.getVenueId(), location.getLocationId())
                                    .map(foundVenue -> {
                                        foundVenue.setVenueSeatsAmount(venue.getVenueSeatsAmount());
                                        foundVenue.setVenueNumber(venue.getVenueNumber());
                                        return venueRepository.save(foundVenue);
                                    })
                                    .orElseGet(() -> venueRepository.save(venue));
                        }
                    }
                    if (locationToCreateOrUpdate.getPriceCategories() != null) {
                        for (PriceCategory priceCategory : locationToCreateOrUpdate.getPriceCategories()) {
                            priceCategoryRepository.findByPriceCategoryIdAndLocation_LocationId(priceCategory.getPriceCategoryId(), location.getLocationId())
                                    .map(foundPriceCategory -> {
                                        foundPriceCategory.setPriceCategoryPrice(priceCategory.getPriceCategoryPrice());
                                        foundPriceCategory.setPriceCategoryNameEN(priceCategory.getPriceCategoryNameEN());
                                        foundPriceCategory.setPriceCategoryNameFR(priceCategory.getPriceCategoryNameFR());
                                        foundPriceCategory.setPriceCategoryNameNL(priceCategory.getPriceCategoryNameNL());
                                        return priceCategoryRepository.save(foundPriceCategory);
                                    })
                                    .orElseGet(() -> priceCategoryRepository.save(priceCategory));
                        }
                    }
                    return locationRepository.save(location);
                })
                .orElseGet(() -> {
                    return locationRepository.save(locationToCreateOrUpdate);
                });
    }

    @CrossOrigin
    @PostMapping(path = "/new")
    @RolesAllowed({"cinemas-admin", "admin"})
    public Location newLocation(@RequestBody Location location) {
        return locationRepository.save(location);
    }

    private LocationDTO createLocationDTO(Location location) {
        if (location == null) {
            return null;
        }
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocationId(location.getLocationId());
        locationDTO.setLocationAddress(location.getLocationAddress());
        locationDTO.setLocationDescriptionEN(location.getLocationDescriptionEN());
        locationDTO.setLocationDescriptionFR(location.getLocationDescriptionFR());
        locationDTO.setLocationDescriptionNL(location.getLocationDescriptionNL());
        locationDTO.setLocationName(location.getLocationName());
        locationDTO.setLocationPhotoUrl(location.getLocationPhotoUrl());

        List<VenueDTO> venueDTOs = new ArrayList<>();
        for (Venue venue : location.getVenues()) {
            VenueDTO venueDTO = dtoUtils.getVenueDTO(venue);
            venueDTOs.add(venueDTO);
        }
        locationDTO.setVenues(venueDTOs);
        List<PriceCategoryDTO> priceCategoryDTOS = new ArrayList<>();
        for (PriceCategory priceCategory : location.getPriceCategories()) {
            PriceCategoryDTO priceCategoryDTO = dtoUtils.getPriceCategoryDTO(priceCategory);
            priceCategoryDTOS.add(priceCategoryDTO);
        }
        locationDTO.setPriceCategories(priceCategoryDTOS);
        return locationDTO;
    }

}
