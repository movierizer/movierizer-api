package fr.movierizer.movierizerapi.controller; /* Pourquoi il y  un soucis dans le package */


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
import org.springframework.web.bind.annotation.RequestParam;

/*This controller class is important for the application, here we can redirect the request to the services associated : here MovieServices
 * and we can see all the movies mapping of my api*/

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieServices movieservices;

    public MovieController(MovieServices movieservice) {
        this.movieservices = movieservice;
    }

    @GetMapping
    public Object getMovies(@RequestParam(value = "query", required = false) String query) {
        if(query != null && !query.isBlank()){
            System.out.println("SEARCH MOVIE MAPPING");
            return movieservices.search(query);
        }else{
            System.out.println("GET ALL MOVIES MAPPING");
            return movieservices.getAllMovies();
        }
    }

    @PostMapping
    public Movie newMovie(@RequestBody Movie newMovie) {
        System.out.println("NEW MOVIE MAPPING");
        return movieservices.newMovie(newMovie);
    }
    
    @GetMapping("/{id}")
    public Movie getOneMovie(@PathVariable Long id) {
        System.out.println("GET ONE MOVIE MAPPING");
        return movieservices.getOneMovie(id);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@RequestBody Movie newMovie, @PathVariable Long id) {
        System.out.println("UPDATE MOVIE MAPPING");
        return movieservices.updateMovie(newMovie, id);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        System.out.println("DELETE MOVIE MAPPING");
        movieservices.deleteMovie(id);
    }
}
