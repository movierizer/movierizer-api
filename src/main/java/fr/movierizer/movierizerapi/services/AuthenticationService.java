package fr.movierizer.movierizerapi.services;

import fr.movierizer.movierizerapi.data.entities.User;
import fr.movierizer.movierizerapi.data.repository.UserRepository;
import fr.movierizer.movierizerapi.model.LoginUserDto;
import fr.movierizer.movierizerapi.model.RegisterUserDto;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registers a new user in the system.
     * The user's password is encoded before being stored.
     * 
     * @param input the data transfer object containing user registration information.
     * @return the saved user entity with an assigned ID.
     */

    public User signup(RegisterUserDto input) {

        User user = new User()
                .setUsername(input.username())
                .setEmail(input.email())
                .setPassword(passwordEncoder.encode(input.password()));

        return userRepository.save(user);
    }

    /**
     * Authenticates a user based on the provided username and password.
     * 
     * @param input the data transfer object containing user login information.
     * @return the user entity associated with the provided username, if the authentication is successful.
     * @throws UsernameNotFoundException if the user with the provided username does not exist.
     */
    public UserDetails authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.username(),
                        input.password()
                )
        );

        return userRepository.findByUsername(input.username())
                    .orElseThrow();
    }
}
