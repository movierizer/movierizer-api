package fr.movierizer.movierizerapi.config.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*Used to catch exceptions present in @RestController files and a behavior are defined in this class for each exception*/
@RestControllerAdvice
public class MovieNotFoundAdvice {
    
    /**
     * Called when a MovieNotFoundException is thrown.
     * Returns a 404 error.
     * 
     * @param ex the exception thrown.
     * @return the error message.
     */
    @ExceptionHandler(MovieNotFoundException.class) /*this method will be called when a MovieNotFoundException is thrown*/
    @ResponseStatus(HttpStatus.NOT_FOUND) /*return a 404 error*/
    public String movieNotFoundHandler(MovieNotFoundException ex){
        return ex.getMessage();
    }
}
