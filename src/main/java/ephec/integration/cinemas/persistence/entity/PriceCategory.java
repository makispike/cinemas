package ephec.integration.cinemas.persistence.entity;

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
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "idCategorie")
    private Integer priceCategoryId;

    @Column(name = "prixCategorie")
    private BigDecimal priceCategoryPrice;

    @Column(name = "nomCategorieFR")
    private String priceCategoryNameFR;

    @Column(name = "nomCategorieNL")
    private String priceCategoryNameNL;

    @Column(name = "nomCategorieEN")
    private String priceCategoryNameEN;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    @OneToMany(mappedBy="priceCategory", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;
}
