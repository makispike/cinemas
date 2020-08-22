package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.control.*;
import ephec.integration.cinemas.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Documentation about these endpoints can be found at {this application's URL}/swagger-ui.html
// REST controller for everything pertaining to the screenings resource
@RestController
@RequestMapping(path="/screening")
public class ScreeningResource {
    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DTOUtils dtoUtils;

    // The CrossOrigin annotation is necessary in order to avoid CORS errors which can be gotten because of a mismatch
    // between client and server
    @CrossOrigin
    @GetMapping(path="/{id}")
    public ScreeningDTO getScreeningById (@PathVariable Integer id) {
        return createScreeningDTO(screeningRepository.findById(id).orElse(null));
    }

    @CrossOrigin
    @GetMapping(path="/all")
    public List<ScreeningDTO> getAllScreenings () {
        List<ScreeningDTO> screeningDTOList = new ArrayList<>();
        for (Screening screening : screeningRepository.findAll()) {
            screeningDTOList.add(createScreeningDTO(screening));
        }
        return screeningDTOList;
    }

    @CrossOrigin
    @PutMapping(path="/update")
    @RolesAllowed({"cinemas-admin", "admin"})
    public Screening updateScreening(@RequestBody Screening screeningToUpdate) {
        return screeningRepository.findById(screeningToUpdate.getScreeningId())
                .map(screening -> {
                    screening.setMovie(screeningToUpdate.getMovie());
                    screening.setScreeningDate(screeningToUpdate.getScreeningDate()); // format YYYY-MM-DD
                    screening.setScreeningTime(screeningToUpdate.getScreeningTime()); // format hh:mm:ss
                    screening.setAvailableSeats(screeningToUpdate.getAvailableSeats());
                    screening.setVenue(screeningToUpdate.getVenue());
                    screening.setVersion(screeningToUpdate.getVersion());
                    return screeningRepository.save(screening);
                })
                .orElseGet(() -> {
                    return screeningRepository.save(screeningToUpdate);
                });
    }

    @CrossOrigin
    @PostMapping(path="/new")
    @RolesAllowed({"cinemas-admin", "admin"})
    public Screening createScreening(@DTO(ScreeningDTO.class) Screening screening) {
        return screeningRepository.save(screening);
    }

    @CrossOrigin
    @GetMapping(path="/bygenre/{label}/{lang}")
    public List<Screening> findScreeningsByGenre(@PathVariable String label, @PathVariable String lang) {
        Genre genre = new Genre();
        List<Screening> screenings = new ArrayList<>();
        if (lang.equalsIgnoreCase("en")) {
            genre = genreRepository.findByGenreLabelEN(label).orElse(null);
        } else if (lang.equalsIgnoreCase("fr")) {
            genre = genreRepository.findByGenreLabelFR(label).orElse(null);
        } else if (lang.equalsIgnoreCase("nl")) {
            genre = genreRepository.findByGenreLabelNL(label).orElse(null);
        }
        if (genre == null) { // avoid doing extra work and losing more time responding
            return null;
        }
        for (Movie movie : genre.getMovies()) {
            screenings.addAll(movie.getScreenings());
        }
        return screenings;
    }

    @CrossOrigin
    @GetMapping(path="/bydate/{date}")
    public List<ScreeningDTO> findScreeningsByDate(@PathVariable String date) {
        List<ScreeningDTO> screeningsForDate = new ArrayList<>();
        for (Screening screening : screeningRepository.findByScreeningDateOrderByScreeningId(LocalDate.parse(date))) {
            screeningsForDate.add(createScreeningDTO(screening));
        }
        return screeningsForDate;
    }

    @CrossOrigin
    @GetMapping(path="/bymovie/{title}/{lang}")
    public Iterable<Screening> findScreeningsByMovie(@PathVariable String title, @PathVariable String lang) {
        Movie movie = new Movie();
        if (lang.equalsIgnoreCase("en")) {
            movie = movieRepository.findByMovieNameEN(title).orElse(null);
        } else if (lang.equalsIgnoreCase("fr")) {
            movie = movieRepository.findByMovieNameFR(title).orElse(null);
        } else if (lang.equalsIgnoreCase("nl")) {
            movie = movieRepository.findByMovieNameNL(title).orElse(null);
        }
        if (movie == null) { // avoid doing extra work and losing more time responding
            return null;
        }
        return screeningRepository.findByMovie_MovieId(movie.getMovieId());
    }

    private ScreeningDTO createScreeningDTO(Screening screening) {
        if (screening == null) {
            return null;
        }
        List<GenreDTO> genreDTOs = new ArrayList<>();
        List<PriceCategoryDTO> priceCategoryDTOs = new ArrayList<>();
        ScreeningDTO screeningDTO;
        screeningDTO = dtoUtils.getScreeningsDTO(screening);
        screeningDTO.setVenue(dtoUtils.getVenueDTO(screening.getVenue()));
        screeningDTO.getVenue().setLocation(dtoUtils.getLocationDTO(screening.getVenue().getLocation()));
        screeningDTO.setMovie(dtoUtils.getMovieDTO(screening.getMovie()));
        for (Genre genre : screening.getMovie().getGenres()) {
            genreDTOs.add(dtoUtils.getGenre(genre));
        }
        screeningDTO.getMovie().setGenres(genreDTOs);
        for (PriceCategory priceCategory : screening.getVenue().getLocation().getPriceCategories()) {
            priceCategoryDTOs.add(dtoUtils.getPriceCategoryDTO(priceCategory));
        }
        screeningDTO.getVenue().getLocation().setPriceCategories(priceCategoryDTOs);
        screeningDTO.setVersion(dtoUtils.getVersion(screening.getVersion()));
        return screeningDTO;
    }

}
