package fr.movierizer.movierizerapi.model;

// Represents a data transfer object for user registration.
public record RegisterUserDto(String email, String password, String username) {
}
