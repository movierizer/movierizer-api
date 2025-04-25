package fr.movierizer.movierizerapi.controller; /* Pourquoi il y  un soucis dans le package */


import java.util.List;
import fr.movierizer.movierizerapi.repository.MovieRepository;
import fr.movierizer.movierizerapi.model.Movie;
import fr.movierizer.movierizerapi.services.MovieServices;

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

    private final MovieServices movieservices;

    public MovieController(MovieServices movieservice) {
        this.movieservices = movieservice;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieservices.getAllMovies();
    }

    @PostMapping
    public Movie newMovie(@RequestBody Movie newMovie) {
        return movieservices.newMovie(newMovie);
    }
    
    @GetMapping("/{isan}")
    public Movie getOneMovie(@PathVariable Long isan) {
        return movieservices.getOneMovie(isan);
    }

    @PutMapping("/{isan}")
    public Movie updateMovie(@RequestBody Movie newMovie, @PathVariable Long isan) {
        return movieservices.updateMovie(newMovie, isan);
    }

    @DeleteMapping("/{isan}")
    public void deleteMovie(@PathVariable Long isan){
        movieservices.deleteMovie(isan);
    }
}
