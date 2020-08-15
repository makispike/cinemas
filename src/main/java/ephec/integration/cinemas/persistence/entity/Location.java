package ephec.integration.cinemas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "complexe")
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idComplexe")
    private Integer locationId;

    @Column(name = "nomComplexe")
    private String locationName;

    @Column(name = "adresseComplexe")
    private String locationAddress;

    @Column(name = "descriptifComplexeFR")
    private String locationDescriptionFR;

    @Column(name = "descriptifComplexeNL")
    private String locationDescriptionNL;

    @Column(name = "descriptifComplexeEN")
    private String locationDescriptionEN;

    @Column(name = "urlPhotoComplexe")
    private String locationPhotoUrl;

    @OneToMany(mappedBy="location", cascade = CascadeType.ALL)
    @JsonManagedReference(value="venue-location")
    private Set<Venue> venues;

    @OneToMany(mappedBy="location", cascade = CascadeType.ALL)
    @JsonManagedReference(value="price-location")
    private Set<PriceCategory> priceCategories;
}
