package fr.movierizer.movierizerapi.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.movierizer.movierizerapi.data.entities.User;
import fr.movierizer.movierizerapi.model.LoginResponse;
import fr.movierizer.movierizerapi.model.LoginUserDto;
import fr.movierizer.movierizerapi.model.RegisterUserDto;
import fr.movierizer.movierizerapi.services.AuthenticationService;
import fr.movierizer.movierizerapi.services.JwtService;



@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }


    /**
     * Register a new user and return the newly created user.
     * 
     * @param registerUserDto the data transfer object containing user registration information.
     * @return the newly created user and a status code of 201 (Created).
     */
    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    /**
     * Authenticates a user based on the provided login information and returns a JWT token if successful.
     * 
     * @param loginUserDto the data transfer object containing user login information.
     * @return a response entity containing the login response with the JWT token and its expiration time and a status code of 200 (OK).
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        var authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
