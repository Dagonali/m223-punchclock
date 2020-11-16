package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.repository.UserRepository;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The User endpoint.
 * GET: {URL}/users - To see all users
 * POST: {URL}/users/login - To log in with a user-account
 * POST: {URL}/users/sign-up - To sign-up a new user-account
 * DELETE: {URL}/users/{id} - To delete a user by the userid
 * PUT: {URL}/users/{id} - To update userdata by the userid
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.getOne(user.getId());
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){return userService.findAll();}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping(value = "/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id){
        user.setId(id);
        return userService.updateUser(user);
    }
}
