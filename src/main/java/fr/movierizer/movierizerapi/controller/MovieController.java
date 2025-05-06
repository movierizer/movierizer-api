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

    /**
     * Mapping to get all movies in the database or to search a movie with a query
     * The method will return a list of movies if the query parameter is not set.
     * If the query parameter is set, the method will return a string containing the result of the search. We can intrepet that like a list of results. 
     * @param query the query string to search movies.
     * @return a list of movies or a string containing the result of the search.
     */
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

    /**
     * Mapping to add a new movie in the database.
     * The method will return the new movie.
     * @param newMovie the movie to add in the database.
     * @return the new movie with the id attribute set.
     */
    @PostMapping
    public Movie newMovie(@RequestBody Movie newMovie) {
        System.out.println("NEW MOVIE MAPPING");
        return movieservices.newMovie(newMovie);
    }
    
    /**
     * Mapping to get a movie by its id in the database.
     * The method will return the movie associated to the id.
     * @param id the id of the movie to get.
     * @return the movie associated to the id.
     */
    @GetMapping("/{id}")
    public Movie getOneMovie(@PathVariable Long id) {
        System.out.println("GET ONE MOVIE MAPPING");
        return movieservices.getOneMovie(id);
    }

    /**
     * Mapping to update a movie in the database.
     * The method will return the updated movie.
     * @param newMovie the updated movie to save in the database.
     * @param id the id of the movie to update.
     * @return the updated movie with the id attribute set.
     */
    @PutMapping("/{id}")
    public Movie updateMovie(@RequestBody Movie newMovie, @PathVariable Long id) {
        System.out.println("UPDATE MOVIE MAPPING");
        return movieservices.updateMovie(newMovie, id);
    }

    /**
     * Mapping to delete a movie from the database.
     * The method will perform a deletion of the movie associated with the specified id.
     * @param id the id of the movie to delete.
     */

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        System.out.println("DELETE MOVIE MAPPING");
        movieservices.deleteMovie(id);
    }
}
