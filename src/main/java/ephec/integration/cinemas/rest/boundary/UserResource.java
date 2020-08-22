package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.control.DTOUtils;
import ephec.integration.cinemas.persistence.control.UserRepository;
import ephec.integration.cinemas.persistence.entity.Reservation;
import ephec.integration.cinemas.persistence.entity.ReservationDTO;
import ephec.integration.cinemas.persistence.entity.User;
import ephec.integration.cinemas.persistence.entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

// Documentation about these endpoints can be found at {this application's URL}/swagger-ui.html
@RestController
@RequestMapping(path="/user")
public class UserResource {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DTOUtils dtoUtils;

    // Whenever this is called, we'll try to find whether a user exists in the DB with that email address
    // If there isn't the user is created. If there is, we update the user.
    @CrossOrigin
    @PutMapping(path="/update")
    @RolesAllowed({"cinemas-admin", "admin", "cinemas-user", "user"})
    public User updateOrCreateUserProfileInfo(@RequestBody User userInfoToUpdateOrToCreate) {
        return userRepository.findByUserEmail(userInfoToUpdateOrToCreate.getUserEmail())
                .map(user -> {
                    user.setUserContactEmail(userInfoToUpdateOrToCreate.getUserContactEmail());
                    user.setUserAddress(userInfoToUpdateOrToCreate.getUserAddress());
                    user.setUserFirstName(userInfoToUpdateOrToCreate.getUserFirstName());
                    user.setUserLastName(userInfoToUpdateOrToCreate.getUserLastName());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    return userRepository.save(userInfoToUpdateOrToCreate);
                });
    }

    @CrossOrigin
    @PostMapping(path="/register")
    @RolesAllowed({"cinemas-admin", "admin", "cinemas-user", "user"})
    public User registerNewUser(@RequestBody String userEmail) {
        User user = new User();
        user.setUserEmail(userEmail);
        return userRepository.save(user);
    }

    @CrossOrigin
    @GetMapping(path="/{id}")
    @RolesAllowed({"cinemas-admin", "admin", "cinemas-user", "user"})
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(Integer.valueOf(id)).orElse(null);
    }

    @CrossOrigin
    @GetMapping(path="/byEmail/{email}")
    public UserDTO getUserByEmail(@PathVariable String email) throws Exception {
        User user = userRepository.findByUserEmail(email).orElse(null);
        return createUserDTO(user);
    }

    @CrossOrigin
    @GetMapping(path="/all")
    @RolesAllowed({"cinemas-admin", "admin", "cinemas-user", "user"})
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userDTOS.add(createUserDTO(user));
        }
        return userDTOS;
    }

    private UserDTO createUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setUserLastName(user.getUserLastName());
        userDTO.setUserFirstName(user.getUserFirstName());
        userDTO.setUserAddress(user.getUserAddress());
        userDTO.setUserContactEmail(user.getUserContactEmail());

        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : user.getReservations()) {
            reservationDTOS.add(dtoUtils.getReservationDTO(reservation));
        }
        userDTO.setReservations(reservationDTOS);
        return userDTO;
    }

}
