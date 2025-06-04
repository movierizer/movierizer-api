package fr.movierizer.movierizerapi.controller; 


import fr.movierizer.movierizerapi.data.DTO.DtoMovieUser;
import fr.movierizer.movierizerapi.data.entities.Movie;
import fr.movierizer.movierizerapi.data.entities.User;
import fr.movierizer.movierizerapi.data.entities.User_movies;
import fr.movierizer.movierizerapi.data.repository.User_movieRepository;
import fr.movierizer.movierizerapi.services.MovieServices;
import fr.movierizer.movierizerapi.services.UserMovieService;
import fr.movierizer.movierizerapi.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*This controller class is important for the application, here we can redirect the request to the services associated : here MovieServices
 * and we can see all the movies mapping of my api*/
@RestController
@RequestMapping("/movies")
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);
    private final MovieServices movieservices;
    private final UserService userService;
    private final User_movieRepository user_movieRepository;
    private final UserMovieService usermovieservices;

    public MovieController(MovieServices movieservice, UserService userService, User_movieRepository user_movierepository, UserMovieService usermovieservices) {     
        this.user_movieRepository = user_movierepository;
        this.userService = userService;
        this.movieservices = movieservice;
        this.usermovieservices = usermovieservices;
    }

    /**
     * Mapping to get all movies in the database or to search a movie with a query
     * The method will return a list of movies if the query parameter is not set.
     * If the query parameter is set, the method will return a string containing the result of the search. We can intrepet that like a list of results.
     *
     * @param query the query string to search movies.
     */
    @GetMapping
    public Object getMovies(@RequestParam(value = "query", required = false) String query) {
        if(query != null && !query.isBlank()){
            log.info("SEARCH MOVIE MAPPING");
            return movieservices.search(query);
        }else{
            log.info("GET ALL MOVIES MAPPING");
            return movieservices.getAllMovies();
        }
    }

    /**
     * Mapping to add a new movie in the database.
     * 
     * @param newMovie the movie to add in the database.
     * @return the new movie with the id attribute set.
     */
    @PostMapping
    public Movie newMovie(@RequestBody Movie newMovie) {
        log.info("NEW MOVIE MAPPING");
        return movieservices.newMovie(newMovie);
    }
    
    /**
     * Mapping to get a movie by its id in the database.
     * The method will return the movie associated to the id.
     * 
     * @param id the id of the movie to get.
     * @return the movie associated to the id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DtoMovieUser> getOneMovie(@RequestHeader String authorization, @PathVariable Long id) {
        log.info("GET ONE MOVIE MAPPING");
        if (authorization != null && authorization.startsWith("Bearer ")) { // TODO see to delete this conditionel
            String token = authorization.substring(7);
            User movieUser = userService.getOneUser(token);
            User_movies user_movie = user_movieRepository.findByUserIdAndMovieId(movieUser.getId(), id); //find if a user have already this movie
            Movie movieToReturned = movieservices.getOneMovie(id); 
            if( user_movie!= null){ //if true return the all data of the movie and the user
                DtoMovieUser userMovie = usermovieservices.getOneMovieWithUser(movieToReturned, movieUser.getId());
                return ResponseEntity.ok(userMovie);
            }else{
                DtoMovieUser userMovie = usermovieservices.getOneMovieWithoutUser(movieToReturned);
                return ResponseEntity.ok(userMovie);
            }
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DtoMovieUser()); //TODO see to return maybe a string or something else 
        }
    }

    /**
     * Mapping to update a movie in the database.
     * The method will return the updated movie.
     * 
     * @param newMovie the updated movie to save in the database.
     * @param id the id of the movie to update.
     * @return the updated movie with the id attribute set.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User_movies> updateMovie(@RequestHeader String authorization, @RequestBody DtoMovieUser updateinfo, @PathVariable Long id) {
        log.info("UPDATE MOVIE MAPPING");
         if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            User movieUser = userService.getOneUser(token);
            User_movies user_movie = user_movieRepository.findByUserIdAndMovieId(movieUser.getId(), id); //find if a user have already this movie
            if(user_movie == null){  // TODO this condition is useless
                User_movies movieUpdated = usermovieservices.updateMovie(updateinfo, id, movieUser.getId());
                return ResponseEntity.ok(movieUpdated);
            }else{
                User_movies movieUpdated = usermovieservices.updateMovie(updateinfo, id, movieUser.getId());
                return ResponseEntity.ok(movieUpdated);
            }
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new User_movies()); //TODO see to return maybe a string or something else 
        }
    }

    /**
     * Mapping to delete a movie from the database.
     * The method will perform a deletion of the movie associated with the specified id.
     * 
     * @param id the id of the movie to delete.
     */

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        log.info("DELETE MOVIE MAPPING");
        movieservices.deleteMovie(id);
    }
}
