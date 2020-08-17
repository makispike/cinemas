package ephec.integration.cinemas.persistence.control;

import ephec.integration.cinemas.persistence.entity.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class DTOUtils {
    public VersionDTO getVersion(Version version) {
        VersionDTO versionDTO = new VersionDTO();
        versionDTO.setVersionId(version.getVersionId());
        versionDTO.setVersionLabel(version.getVersionLabel());
        return versionDTO;
    }

    public GenreDTO getGenre(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setGenreId(genre.getGenreId());
        genreDTO.setGenreLabelEN(genre.getGenreLabelEN());
        genreDTO.setGenreLabelFR(genre.getGenreLabelFR());
        genreDTO.setGenreLabelNL(genre.getGenreLabelNL());
        return genreDTO;
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

    public PriceCategoryDTO getPriceCategoryDTO(PriceCategory priceCategory) {
        PriceCategoryDTO priceCategoryDTO = new PriceCategoryDTO();
        priceCategoryDTO.setPriceCategoryId(priceCategory.getPriceCategoryId());
        priceCategoryDTO.setPriceCategoryNameEN(priceCategory.getPriceCategoryNameEN());
        priceCategoryDTO.setPriceCategoryNameFR(priceCategory.getPriceCategoryNameFR());
        priceCategoryDTO.setPriceCategoryNameNL(priceCategory.getPriceCategoryNameNL());
        priceCategoryDTO.setPriceCategoryPrice(priceCategory.getPriceCategoryPrice());
        return priceCategoryDTO;
    }

    public ReservationDTO getReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setReservationDateTime(reservation.getReservationDateTime());
        return reservationDTO;
    }

    public TicketDTO getTicketDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(ticket.getTicketId());
        return ticketDTO;
    }

    public UserDTO getUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setUserFirstName(user.getUserFirstName());
        userDTO.setUserLastName(user.getUserLastName());
        userDTO.setUserAddress(user.getUserAddress());
        userDTO.setUserAge(user.getUserAge());
        userDTO.setUserContactEmail(user.getUserContactEmail());
        return userDTO;
    }
}
