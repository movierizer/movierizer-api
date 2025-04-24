package fr.movierizer.movierizerapi.exception;

public class MovieNotFoundException extends RuntimeException {
    
    public MovieNotFoundException(Long isan) {
        super("Could not find movie " + isan);
    }
}
