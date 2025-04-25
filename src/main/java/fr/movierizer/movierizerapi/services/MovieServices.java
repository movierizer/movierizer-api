package fr.movierizer.movierizerapi.services;

import java.util.List;

import fr.movierizer.movierizerapi.repository.MovieRepository;
import fr.movierizer.movierizerapi.model.Movie;
import fr.movierizer.movierizerapi.exception.*; 
import org.springframework.stereotype.Service;


@Service 
public class MovieServices {

    private final MovieRepository movierepository;

    public MovieServices(MovieRepository movierepository) {
        this.movierepository = movierepository;
    }

    public List<Movie> getAllMovies() {
        return movierepository.findAll();
    }

    public Movie getOneMovie(Long isan) {
        return movierepository.findById(isan)
                .orElseThrow(() -> new MovieNotFoundException(isan));
    }

    public Movie newMovie(Movie newMovie) {
        return movierepository.save(newMovie);
    }

    public Movie updateMovie(Movie newMovie, Long isan) {
        return movierepository.findById(isan)
                .map(movie -> {
                    movie.setTitle(newMovie.getTitle());
                    movie.setDescription(newMovie.getDescription());
                    movie.setGrade(newMovie.getGrade());
                    return movierepository.save(movie);
                })
                .orElseThrow(() -> new MovieNotFoundException(isan));
    }

    public void deleteMovie(Long isan){
        movierepository.deleteById(isan);
    }
    
}
