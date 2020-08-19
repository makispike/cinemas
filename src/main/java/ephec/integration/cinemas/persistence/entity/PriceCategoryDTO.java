package ephec.integration.cinemas.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
public class PriceCategoryDTO {
    @Id
    private Integer priceCategoryId;
    private BigDecimal priceCategoryPrice;
    private String priceCategoryNameFR;
    private String priceCategoryNameNL;
    private String priceCategoryNameEN;
}
