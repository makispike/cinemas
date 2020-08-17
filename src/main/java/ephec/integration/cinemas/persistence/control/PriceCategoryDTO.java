package ephec.integration.cinemas.persistence.control;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PriceCategoryDTO {
    private Integer priceCategoryId;
    private BigDecimal priceCategoryPrice;
    private String priceCategoryNameFR;
    private String priceCategoryNameNL;
    private String priceCategoryNameEN;
}
