package fr.movierizer.movierizerapi.exception;

/*This is a class for the exception MovieNotFoundException*/
public class MovieNotFoundException extends RuntimeException {
    
    public MovieNotFoundException(Long id) {
        super("Could not find movie " + id);
    }
}
