package fr.movierizer.movierizerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.movierizer.movierizerapi.model.User;
import fr.movierizer.movierizerapi.services.UserService;

@RestController
@RequestMapping("/")
public class UserController {
    
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user in the system.
     * 
     * The method checks if a user with the provided email already exists.
     * If the email is already in use, it returns a message indicating so.
     * Otherwise, it saves the new user and returns a success message.
     * 
     * @param user the User object containing registration details
     * @return a string message indicating the result of the registration
     */
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null) {
            return "Email already exists";
        }
        userService.saveUser(user);
        return "User registered successfully";
    }

    /**
     * Handles a login request.
     * 
     * The method checks if a user with the provided email exists and if the provided password matches the one stored in the database.
     * If the credentials are valid, it returns a success message.
     * Otherwise, it returns an error message.
     * 
     * @param user the User object containing login credentials
     * @return a string message indicating the result of the login
     */
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }
}
