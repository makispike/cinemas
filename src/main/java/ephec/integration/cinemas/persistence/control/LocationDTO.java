package ephec.integration.cinemas.persistence.control;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private List<PriceCategoryDTO> priceCategories;

}
