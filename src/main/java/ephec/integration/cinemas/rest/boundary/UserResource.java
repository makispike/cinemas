package ephec.integration.cinemas.rest.boundary;

import ephec.integration.cinemas.persistence.boundary.UserRepository;
import ephec.integration.cinemas.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Documentation about these endpoints can be found at {this application's URL}/swagger-ui.html
@RestController // This means that this class is a Controller
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class UserResource {
    @Autowired
    private UserRepository userRepository;

    // Whenever this is called, we'll try to find whether a user exists in the DB with that email address
    // If there isn't the user is created. If there is, we update the user.

    // ADD PRINCIPAL TO CHECK AUTHENTICATION AND USE THAT EMAIL TO SEARCH THE USER
    @CrossOrigin
    @PutMapping(path="/update")
    public User updateOrCreateUserProfileInfo(@RequestBody User userInfoToUpdateOrToCreate) {
        return userRepository.findByUserEmail(userInfoToUpdateOrToCreate.getUserEmail())
                .map(user -> {
                    user.setUserContactEmail(userInfoToUpdateOrToCreate.getUserContactEmail());
                    user.setUserAddress(userInfoToUpdateOrToCreate.getUserAddress());
                    user.setUserAge(userInfoToUpdateOrToCreate.getUserAge());
                    user.setUserFirstName(userInfoToUpdateOrToCreate.getUserFirstName());
                    user.setUserLastName(userInfoToUpdateOrToCreate.getUserFirstName());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    return userRepository.save(userInfoToUpdateOrToCreate);
                });
    }

    // ADD PRINCIPAL TO CHECK AUTHENTICATION AND ROLE
    @CrossOrigin
    @PostMapping(path="/register")
    public User registerNewUser(@RequestBody String email) {
        User user = new User();
        user.setUserEmail(email);
        return userRepository.save(user);
    }

    @CrossOrigin
    @GetMapping(path="/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(Integer.valueOf(id)).orElse(null);
    }

    @CrossOrigin
    @GetMapping(path="/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}
