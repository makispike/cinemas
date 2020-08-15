package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.boundary.GenreRepository;
import ephec.integration.cinemas.persistence.boundary.MovieRepository;
import ephec.integration.cinemas.persistence.boundary.ScreeningRepository;
import ephec.integration.cinemas.persistence.entity.Genre;
import ephec.integration.cinemas.persistence.entity.Movie;
import ephec.integration.cinemas.persistence.entity.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    // The CrossOrigin annotation is necessary in order to avoid CORS errors which can be gotten because of a mismatch
    // between client and server
    @CrossOrigin
    @GetMapping(path="/{id}")
    public Screening getScreeningById (@PathVariable Integer id) {
        return screeningRepository.findById(id).orElse(null);
    }

    @CrossOrigin
    @GetMapping(path="/all")
    public Iterable<Screening> getAllScreenings () {
        return screeningRepository.findAll();
    }

    @CrossOrigin
    @PutMapping(path="/update")
    public Screening updateScreening(@RequestBody Screening screeningToUpdate) {
        return screeningRepository.findById(screeningToUpdate.getScreeningId())
                .map(screening -> {
                    screening.setMovie(screeningToUpdate.getMovie());
                    screening.setScreeningDate(screeningToUpdate.getScreeningDate()); // format YYYY-MM-DD
                    screening.setScreeningTime(screeningToUpdate.getScreeningTime()); // format hh:mm:ss
                    screening.setAvailableSeats(screeningToUpdate.getAvailableSeats());
                    screening.setVenue(screeningToUpdate.getVenue());
                    return screeningRepository.save(screening);
                })
                .orElseGet(() -> {
                    return screeningRepository.save(screeningToUpdate);
                });
    }

    @CrossOrigin
    @PostMapping(path="/new")
    public Screening createScreening(@RequestBody Screening screening) {
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
    public Iterable<Screening> findScreeningsByDate(@PathVariable String date) {
        return screeningRepository.findByScreeningDateOrderByScreeningId(LocalDate.parse(date));
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

}
