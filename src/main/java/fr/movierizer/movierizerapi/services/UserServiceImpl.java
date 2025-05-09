package fr.movierizer.movierizerapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.movierizer.movierizerapi.model.User;
import fr.movierizer.movierizerapi.repository.UserRepository;

/* This class implemented the methods who are defined in UsersService */
@Service
public class UserServiceImpl implements UserService {
    

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Returns a user by their email.
     *
     * @param email the email to search for
     * @return the user associated with the provided email, or null if no such user exists
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Saves a user to the repository after encoding the user's password.
     *
     * @param user the user to save, with their password to be encoded
     * @return the saved user with the encoded password
     */

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
