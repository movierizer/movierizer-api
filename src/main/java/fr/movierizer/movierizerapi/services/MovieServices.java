package fr.movierizer.movierizerapi.services;

import java.util.List;

import fr.movierizer.movierizerapi.repository.MovieRepository;
import fr.movierizer.movierizerapi.model.Movie;
import fr.movierizer.movierizerapi.exception.*; 
import org.springframework.stereotype.Service;

/*This class is call by controller and he represent all of the logic of my application. What to do in wich situation*/
@Service 
public class MovieServices {

    private final MovieRepository movierepository;

    public MovieServices(MovieRepository movierepository) {
        this.movierepository = movierepository;
    }

    public List<Movie> getAllMovies() {
        return movierepository.findAll();
    }

    public Movie getOneMovie(Long id) {
        return movierepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    public Movie newMovie(Movie newMovie) {
        return movierepository.save(newMovie);
    }

    public Movie updateMovie(Movie newMovie, Long id) {
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
        movierepository.deleteById(id);
    }
    
}
