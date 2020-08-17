package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class DTOUtils {
    public List<VersionDTO> getVersions(Set<Version> set) {
        List<VersionDTO> versionDTOs = new ArrayList<>();
        for (Version version : set) {
            VersionDTO versionDTO = new VersionDTO();
            versionDTO.setVersionId(version.getVersionId());
            versionDTO.setVersionLabel(version.getVersionLabel());
            versionDTOs.add(versionDTO);
        }
        return versionDTOs;
    }

    public List<GenreDTO> getGenres(Set<Genre> set) {
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

    public ScreeningDTO getScreeningsDTO(Screening screening) {
        ScreeningDTO screeningDTO = new ScreeningDTO();
        screeningDTO.setScreeningId(screening.getScreeningId());
        screeningDTO.setScreeningDate(screening.getScreeningDate());
        screeningDTO.setScreeningTime(screening.getScreeningTime());
        screeningDTO.setAvailableSeats(screening.getAvailableSeats());
        return screeningDTO;
    }

    public MovieDTO getMovieDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(movie.getMovieId());
        movieDTO.setMovieDescriptionEN(movie.getMovieDescriptionEN());
        movieDTO.setMovieDescriptionFR(movie.getMovieDescriptionFR());
        movieDTO.setMovieDescriptionNL(movie.getMovieDescriptionNL());
        movieDTO.setMovieNameEN(movie.getMovieNameEN());
        movieDTO.setMovieNameFR(movie.getMovieNameFR());
        movieDTO.setMovieNameNL(movie.getMovieNameNL());
        movieDTO.setMoviePictureUrl(movie.getMoviePictureUrl());
        return movieDTO;
    }

    public VenueDTO getVenueDTO(Venue venue) {
        VenueDTO venueDTO = new VenueDTO();
        venueDTO.setVenueId(venue.getVenueId());
        venueDTO.setVenueNumber(venue.getVenueNumber());
        venueDTO.setVenueSeatsAmount(venue.getVenueSeatsAmount());
        return venueDTO;
    }

    public LocationDTO getLocationDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocationId(location.getLocationId());
        locationDTO.setLocationName(location.getLocationName());
        locationDTO.setLocationAddress(location.getLocationAddress());
        locationDTO.setLocationDescriptionEN(location.getLocationDescriptionEN());
        locationDTO.setLocationDescriptionFR(location.getLocationDescriptionFR());
        locationDTO.setLocationDescriptionNL(location.getLocationDescriptionNL());
        locationDTO.setLocationPhotoUrl(location.getLocationPhotoUrl());
        return locationDTO;
    }
}
