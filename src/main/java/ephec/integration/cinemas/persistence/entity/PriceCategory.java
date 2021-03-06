package ephec.integration.cinemas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "categorie")
public class PriceCategory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idCategorie")
    private Integer priceCategoryId;

    @Column(name = "prixCategorie")
    private BigDecimal priceCategoryPrice;

    @Column(name = "nomCategorieFR", nullable = false, unique = true)
    private String priceCategoryNameFR;

    @Column(name = "nomCategorieNL", nullable = false, unique = true)
    private String priceCategoryNameNL;

    @Column(name = "nomCategorieEN", nullable = false, unique = true)
    private String priceCategoryNameEN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="complexe_idComplexe")
    @JsonBackReference(value="price-location")
    private Location location;

    @OneToMany(mappedBy="priceCategory", cascade = CascadeType.ALL)
    @JsonManagedReference(value="price-ticket")
    private Set<Ticket> tickets;
}
