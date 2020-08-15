package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.boundary.PriceCategoryRepository;
import ephec.integration.cinemas.persistence.entity.PriceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/price")
public class PriceCategoryResource {
    @Autowired
    private PriceCategoryRepository priceCategoryRepository;

    @CrossOrigin
    @GetMapping(path = "/all")
    public Iterable<PriceCategory> getAllPrices() {
        return priceCategoryRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    public PriceCategory getPriceCategoryById(@PathVariable Integer id) {
        return priceCategoryRepository.findById(id).orElse(null);
    }

    @CrossOrigin
    @GetMapping(path = "/bylocation/{locationId}")
    public Iterable<PriceCategory> getAllPricesForLocation(@PathVariable String locationId) {
        return priceCategoryRepository.findByLocation_LocationId(Integer.valueOf(locationId));
    }

    @CrossOrigin
    @PutMapping(path="/update")
    public PriceCategory createOrUpdatePriceCategory(@RequestBody PriceCategory priceCategoryToUpdateOrCreate) {
        return priceCategoryRepository.findById(priceCategoryToUpdateOrCreate.getPriceCategoryId())
                .map(priceCategory -> {
                    priceCategory.setPriceCategoryPrice(priceCategoryToUpdateOrCreate.getPriceCategoryPrice());
                    priceCategory.setPriceCategoryNameEN(priceCategoryToUpdateOrCreate.getPriceCategoryNameEN());
                    priceCategory.setPriceCategoryNameFR(priceCategoryToUpdateOrCreate.getPriceCategoryNameFR());
                    priceCategory.setPriceCategoryNameNL(priceCategoryToUpdateOrCreate.getPriceCategoryNameNL());
                    return priceCategoryRepository.save(priceCategory);
                })
                .orElseGet(() -> {
                    return priceCategoryRepository.save(priceCategoryToUpdateOrCreate);
                });
    }

    @CrossOrigin
    @PostMapping(path="/new")
    public PriceCategory createPriceCategory(@RequestBody PriceCategory priceCategory) {
        return priceCategoryRepository.save(priceCategory);
    }

}
