package fr.movierizer.movierizerapi.controller; /* Pourquoi il y  un soucis dans le package */


import java.util.List;
import fr.movierizer.movierizerapi.repository.MovieRepository;
import fr.movierizer.movierizerapi.model.Movie;
import fr.movierizer.movierizerapi.exception.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movierepository;

    public MovieController(MovieRepository repository) {
        this.movierepository = repository;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movierepository.findAll();
    }

    @PostMapping
    public Movie newMovie(@RequestBody Movie newMovie) {
        return movierepository.save(newMovie);
    }
    
    @GetMapping("/{isan}")
    public Movie getOneMovie(@PathVariable Long isan) {
        return movierepository.findById(isan)
                .orElseThrow(() -> new MovieNotFoundException(isan));
    }

    @PutMapping("/{isan}")
    public Movie updateMovie(@RequestBody Movie newMovie, @PathVariable Long isan) {
        
        return movierepository.findById(isan)
                .map(movie -> {
                    movie.setTitle(newMovie.getTitle());
                    movie.setDescription(newMovie.getDescription());
                    movie.setGrade(newMovie.getGrade());
                    return movierepository.save(movie);
                })
                .orElseThrow(() -> new MovieNotFoundException(isan));
    }

    @DeleteMapping("/{isan}")
    public void deleteMovie(@PathVariable Long isan){
        movierepository.deleteById(isan);
    }
}
