package fr.movierizer.movierizerapi.services;

import java.util.List;

import fr.movierizer.movierizerapi.config.exception.*;
import fr.movierizer.movierizerapi.data.entities.Movie;
import fr.movierizer.movierizerapi.data.repository.MovieRepository;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*This class is call by controller and he represent all of the logic of my application. What to do in wich situation*/
@Service 
public class MovieServices {

    private static final Logger log = LoggerFactory.getLogger(MovieServices.class);
    private final MovieRepository movierepository;
    private final ApiService apiService;

    public MovieServices(MovieRepository movierepository, ApiService apiService) {
        this.movierepository = movierepository;
        this.apiService = apiService;
    }

    /**
     * This method will return a list of all movies available in the repository, so inderectly in the database.
     * 
     * @return a list of all movies available in the repository.
     */
    public List<Movie> getAllMovies() {
        log.info("GET ALL MOVIES");
        return movierepository.findAll();
    }

    /**
     * Retrieves a movie by its ID from the TMDB API.
     * If the movie is not found, throws a MovieNotFoundException.
     * 
     * @param id the ID of the movie to retrieve.
     * @return the movie associated with the provided ID.
     * @throws MovieNotFoundException if the movie is not found.
     */
    public Movie getOneMovie(Long id) {
        log.info("GET ONE MOVIE");
            Movie movie = apiService.getOneMovie(id).block();
            if (movie == null) {
                throw new MovieNotFoundException(id);
            }
            return movie;
    }

    /**
     * Searches for movies using the provided query string.
     * This method utilizes the ApiService to perform the search operation. 
     * 
     * @param query the query string to search for movies.
     * @return a string containing the result of the search call to the TMDB API.
     */
    public String search(String query) {
        log.info("SEARCH MOVIE");
        return apiService.searchMovie(query).block();
    }

    /**
     * Adds a new movie to the repository. And call the TMDB API to retrieve the movie details.
     * 
     * @param newMovie the movie to be added to the repository.
     * @return the newly added movie with its ID set.
     */

    public Movie newMovie(Movie newMovie) {
        String result = apiService.getOneMovie(newMovie).block(); 
        return movierepository.save(newMovie);
    }

    /**
     * Updates a movie in the repository. 
     * 
     * @param newMovie the movie with the updated information.
     * @param id the ID of the movie to update.
     * @return the updated movie.
     * @throws MovieNotFoundException if the movie is not found in the repository.
     */
    public Movie updateMovie(Movie newMovie, Long id) {
        log.info("UPDATE MOVIE");
        return movierepository.findById(id)
                .map(movie -> {
                    movie.setTitle(newMovie.getTitle());
                    movie.setOverview(newMovie.getOverview());
                    return movierepository.save(movie);
                })
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    /**
     * Deletes a movie from the repository.
     * 
     * @param id the ID of the movie to delete.
     */
    public void deleteMovie(Long id){
        log.info("DELETE MOVIE");
        movierepository.deleteById(id);
    }
    
}
