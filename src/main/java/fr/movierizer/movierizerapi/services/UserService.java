package fr.movierizer.movierizerapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.movierizer.movierizerapi.data.entities.User;
import fr.movierizer.movierizerapi.data.repository.UserRepository;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService =  jwtService;
    }

    public User getOneUser(String token) {
        log.info("GET ONE USER");
        String username = jwtService.extractUsername(token);
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User updateOneUSer(String token, User updateUser) {
        log.info("UPDATE A USER");
        User userToUpdate = getOneUser(token);

        userToUpdate.setEmail(updateUser.getEmail());
        userToUpdate.setPassword(updateUser.getPassword());
        userToUpdate.setUsername(updateUser.getUsername());
        userToUpdate.setRole(updateUser.getRole());
        userToUpdate.setProfile_picture(updateUser.getProfile_picture());
        userToUpdate.setUser_language(updateUser.getUser_language());
        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    public void deleteOneUser(String token) {
        log.info("DELETE A USER");
        User userDelete = getOneUser(token);
        userRepository.delete(userDelete);
    }
    
    public User updateToken(String token, String tokenTMDB) {
        log.info("UPDATE USER TOKEN");
        User userToUpdate = getOneUser(token);
        userToUpdate.setTokenTMDB(tokenTMDB);
        userRepository.save(userToUpdate);
        return userToUpdate;
    }
}