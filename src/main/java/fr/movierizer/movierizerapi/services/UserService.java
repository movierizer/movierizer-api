package fr.movierizer.movierizerapi.services;

import fr.movierizer.movierizerapi.model.User;

/* This interface is used to define the methods that will be implemented in UserServiceImpl */
public interface UserService {
    User findByEmail(String email);
    User saveUser(User user);
}
