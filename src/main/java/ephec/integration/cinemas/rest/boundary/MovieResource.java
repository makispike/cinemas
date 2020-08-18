package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.boundary.MovieRepository;
import ephec.integration.cinemas.persistence.control.*;
import ephec.integration.cinemas.persistence.entity.Genre;
import ephec.integration.cinemas.persistence.entity.Movie;
import ephec.integration.cinemas.persistence.entity.Screening;
import ephec.integration.cinemas.persistence.entity.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Documentation about these endpoints can be found at {this application's URL}/swagger-ui.html
@RestController
@RequestMapping(path="/movies") // This means URL's start with /demo (after Application path)
public class MovieResource {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private DTOUtils dtoUtils;

    @CrossOrigin
    @GetMapping(path="/all")
    public List<MovieDTO> getAllMovies () {
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (Movie movie : movieRepository.findAll()) {
            List<GenreDTO> genreDTOs = new ArrayList<>();
            List<VersionDTO> versions = new ArrayList<>();
            List<ScreeningDTO> screeningDTOS = new ArrayList<>();
            MovieDTO movieDTO = dtoUtils.getMovieDTO(movie);
            for (Genre genre : movie.getGenres()) {
                genreDTOs.add(dtoUtils.getGenre(genre));
            }
            movieDTO.setGenres(genreDTOs);
            for (Version version : movie.getVersions()) {
                versions.add(dtoUtils.getVersion(version));
            }
            movieDTO.setVersions(versions);
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
    public Movie createMovie(@DTO(MovieDTO.class) Movie movie) {
        return movieRepository.save(movie);
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
}
