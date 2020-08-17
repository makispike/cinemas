package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.boundary.*;
import ephec.integration.cinemas.persistence.control.*;
import ephec.integration.cinemas.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// Documentation about these endpoints can be found at {this application's URL}/swagger-ui.html
@RestController
@RequestMapping(path="/movies") // This means URL's start with /demo (after Application path)
public class MovieResource {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private VersionRepository versionRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ScreeningRepository screeningRepository;
    @Autowired
    private DTOUtils dtoUtils;

    @CrossOrigin
    @GetMapping(path="/all")
    public List<MovieDTO> getAllMovies () {
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (Movie movie : movieRepository.findAll()) {
            MovieDTO movieDTO = dtoUtils.getMovieDTO(movie);
            movieDTO.setGenres(dtoUtils.getGenres(movie.getGenres()));
            movieDTO.setVersions(dtoUtils.getVersions(movie.getVersions()));
            List<ScreeningDTO> screeningDTOS = new ArrayList<>();
            for (Screening screening : movie.getScreenings()) {
                screeningDTOS.add(dtoUtils.getScreeningsDTO(screening));
            }
            movieDTO.setScreenings(screeningDTOS);
            movieDTOList.add(movieDTO);
        }
        return movieDTOList;
    }

    @CrossOrigin
    @GetMapping(path="/{id}")
    public Movie getMovieById(@PathVariable String id) {
        return movieRepository.findById(Integer.valueOf(id)).orElse(null);
    }

    @CrossOrigin
    @PostMapping(path="/create")
    public Movie newMovie(@RequestBody Movie newMovie) {
        return movieRepository.save(newMovie);
    }

    @CrossOrigin
    @PutMapping(path="/update")
    public Movie createOrUpdateMovie(@RequestBody Movie movieToCreateOrUpdate) {
        return movieRepository.findById(movieToCreateOrUpdate.getMovieId())
                .map(movie -> {
                    movie.setMovieNameEN(movieToCreateOrUpdate.getMovieNameEN());
                    movie.setMovieNameFR(movieToCreateOrUpdate.getMovieNameFR());
                    movie.setMovieNameNL(movieToCreateOrUpdate.getMovieNameNL());
                    movie.setMovieDescriptionEN(movieToCreateOrUpdate.getMovieDescriptionEN());
                    movie.setMovieDescriptionFR(movieToCreateOrUpdate.getMovieDescriptionFR());
                    movie.setMovieDescriptionNL(movieToCreateOrUpdate.getMovieDescriptionNL());
                    movie.setMoviePictureUrl(movieToCreateOrUpdate.getMoviePictureUrl());
                    movie.setScreenings(movieToCreateOrUpdate.getScreenings());
                    movie.setVersions(movieToCreateOrUpdate.getVersions());
                    movie.setGenres(movieToCreateOrUpdate.getGenres());
                    return movieRepository.save(movie);
                })
                .orElseGet(() -> {
                    return movieRepository.save(movieToCreateOrUpdate);
                });
    }

//    @GetMapping(path="/all")
//    public @ResponseBody Iterable<User> getAllUsers() {
//        // This returns a JSON or XML with the users
//        return userRepository.findAll();
//    }
}
