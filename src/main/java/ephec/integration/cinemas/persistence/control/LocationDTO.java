package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.PriceCategory;
import ephec.integration.cinemas.persistence.entity.Venue;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class LocationDTO {
    private Integer locationId;
    private String locationName;
    private String locationAddress;
    private String locationDescriptionFR;
    private String locationDescriptionNL;
    private String locationDescriptionEN;
    private String locationPhotoUrl;
    private List<VenueDTO> venues;
    private List<PriceCategory> priceCategories;

}