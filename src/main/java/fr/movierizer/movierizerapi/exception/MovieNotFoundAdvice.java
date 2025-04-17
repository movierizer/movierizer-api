package fr.movierizer.movierizerapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* Sert a intercepter les exceptions présentes dans les fichier @RestController */
@RestControllerAdvice
public class MovieNotFoundAdvice {
    
    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) /* Va renvoyer une erreur 404 */
    public String movieNotFoundHandler(MovieNotFoundException ex){
        return ex.getMessage();
    }
}
