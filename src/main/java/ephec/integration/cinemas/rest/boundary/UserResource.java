package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.control.UserRepository;
import ephec.integration.cinemas.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

// Documentation about these endpoints can be found at {this application's URL}/swagger-ui.html
@RestController
@RequestMapping(path="/user")
public class UserResource {
    @Autowired
    private UserRepository userRepository;

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
                    user.setUserLastName(userInfoToUpdateOrToCreate.getUserFirstName());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    return userRepository.save(userInfoToUpdateOrToCreate);
                });
    }

    @CrossOrigin
    @PostMapping(path="/register")
    @RolesAllowed({"cinemas-admin", "admin", "cinemas-user", "user"})
    public User registerNewUser(@RequestBody String email) {
        User user = new User();
        user.setUserEmail(email);
        return userRepository.save(user);
    }

    @CrossOrigin
    @GetMapping(path="/{id}")
    @RolesAllowed({"cinemas-admin", "admin", "cinemas-user", "user"})
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(Integer.valueOf(id)).orElse(null);
    }

    @CrossOrigin
    @GetMapping(path="/all")
    @RolesAllowed({"cinemas-admin", "admin"})
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}
