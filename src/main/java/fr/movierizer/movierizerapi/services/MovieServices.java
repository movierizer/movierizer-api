package fr.movierizer.movierizerapi.services;

import java.util.List;

import fr.movierizer.movierizerapi.repository.MovieRepository;
import fr.movierizer.movierizerapi.model.Movie;
import fr.movierizer.movierizerapi.exception.*; 
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

    public List<Movie> getAllMovies() {
        log.info("GET ALL MOVIES");
        return movierepository.findAll();
    }

    public Movie getOneMovie(Long id) {
        log.info("GET ONE MOVIE");
        return movierepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    public String search(String query) {
        log.info("SEARCH MOVIE");
        return apiService.searchMovie(query).block();
    }

    public Movie newMovie(Movie newMovie) {
        log.info("adding movie: " + newMovie);
        String result = apiService.getOneMovie(newMovie).block(); 
        System.out.println("REPONSE DE L'API : " + result);
        System.out.println(newMovie);
        return movierepository.save(newMovie);
    }

    public Movie updateMovie(Movie newMovie, Long id) {
        log.info("UPDATE MOVIE");
        return movierepository.findById(id)
                .map(movie -> {
                    movie.setTitle(newMovie.getTitle());
                    movie.setDescription(newMovie.getDescription());
                    movie.setGrade(newMovie.getGrade());
                    return movierepository.save(movie);
                })
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    public void deleteMovie(Long id){
        log.info("DELETE MOVIE");
        movierepository.deleteById(id);
    }
    
}
