package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.boundary.MovieRepository;
import ephec.integration.cinemas.persistence.control.GenreDTO;
import ephec.integration.cinemas.persistence.control.MovieDTO;
import ephec.integration.cinemas.persistence.control.VersionDTO;
import ephec.integration.cinemas.persistence.entity.Genre;
import ephec.integration.cinemas.persistence.entity.Movie;
import ephec.integration.cinemas.persistence.entity.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="/movies") // This means URL's start with /demo (after Application path)
public class MovieResource {
    @Autowired
    private MovieRepository movieRepository;

    @CrossOrigin
    @GetMapping(path="/all")
    public List<MovieDTO> getAllMovies () {
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (Movie movie : movieRepository.findAll()) {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setMovieId(movie.getMovieId());
            movieDTO.setMovieDescriptionEN(movie.getMovieDescriptionEN());
            movieDTO.setMovieDescriptionFR(movie.getMovieDescriptionFR());
            movieDTO.setMovieDescriptionNL(movie.getMovieDescriptionNL());
            movieDTO.setMovieNameEN(movie.getMovieNameEN());
            movieDTO.setMovieNameFR(movie.getMovieNameFR());
            movieDTO.setMovieNameNL(movie.getMovieNameNL());
            movieDTO.setMoviePictureUrl(movie.getMoviePictureUrl());
            movieDTO.setGenres(getGenres(movie.getGenres()));
            movieDTO.setVersions(getVersions(movie.getVersions()));
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
    public Movie createOrUpdateScreening(@RequestBody Movie movieToCreateOrUpdate) {
        return movieRepository.findById(movieToCreateOrUpdate.getMovieId())
                .map(movie -> {
                    movie.setMovieNameEN(movieToCreateOrUpdate.getMovieNameEN());
                    movie.setMovieNameFR(movieToCreateOrUpdate.getMovieNameFR());
                    movie.setMovieNameNL(movieToCreateOrUpdate.getMovieNameNL());
                    movie.setMovieDescriptionEN(movieToCreateOrUpdate.getMovieDescriptionEN());
                    movie.setMovieDescriptionFR(movieToCreateOrUpdate.getMovieDescriptionFR());
                    movie.setMovieDescriptionNL(movieToCreateOrUpdate.getMovieDescriptionNL());
                    movie.setMoviePictureUrl(movieToCreateOrUpdate.getMoviePictureUrl());
                    movie.setVersions(movieToCreateOrUpdate.getVersions());
                    movie.setGenres(movieToCreateOrUpdate.getGenres());
                    return movieRepository.save(movie);
                })
                .orElseGet(() -> {
                    return movieRepository.save(movieToCreateOrUpdate);
                });
    }

    private List<VersionDTO> getVersions(Set<Version> set) {
        List<VersionDTO> versionDTOs = new ArrayList<>();
        for (Version version : set) {
            VersionDTO versionDTO = new VersionDTO();
            versionDTO.setVersionId(version.getVersionId());
            versionDTO.setVersionLabel(version.getVersionLabel());
            versionDTOs.add(versionDTO);
        }
        return versionDTOs;
    }

    private List<GenreDTO> getGenres(Set<Genre> set) {
        List<GenreDTO> genreDTOs = new ArrayList<>();
        for (Genre genre : set) {
            GenreDTO genreDTO = new GenreDTO();
            genreDTO.setGenreId(genre.getGenreId());
            genreDTO.setGenreLabelEN(genre.getGenreLabelEN());
            genreDTO.setGenreLabelFR(genre.getGenreLabelFR());
            genreDTO.setGenreLabelNL(genre.getGenreLabelNL());
            genreDTOs.add(genreDTO);
        }
        return genreDTOs;
    }

//    @GetMapping(path="/all")
//    public @ResponseBody Iterable<User> getAllUsers() {
//        // This returns a JSON or XML with the users
//        return userRepository.findAll();
//    }
}
