package fr.movierizer.movierizerapi.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import fr.movierizer.movierizerapi.data.DTO.MovieDTO;
import fr.movierizer.movierizerapi.data.entities.Movie;
import fr.movierizer.movierizerapi.data.entities.People_movie;
import fr.movierizer.movierizerapi.data.entities.User;
import fr.movierizer.movierizerapi.mapper.MovieMapper;
import fr.movierizer.movierizerapi.mapper.PeopleMovieMapper;
import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private static final Logger log = LoggerFactory.getLogger(ApiService.class);
    private final WebClient webClient;
    private final PeopleMovieMapper peopleMovieMapper;
    private final MovieMapper movieMapper;

    /* This constructor is used to create an instance of ApiService and initialize the WebClient with the necessary configuration */
	public ApiService(WebClient.Builder webClientBuilder , PeopleMovieMapper peopleMovieMapper, MovieMapper mapper) {
        this.movieMapper = mapper;
        this.peopleMovieMapper = peopleMovieMapper;
		this.webClient = webClientBuilder
            .baseUrl(System.getenv("API_TMDB_URL_SOURCE"))
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build();
	}

    private String getConnectedUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
             log.warn("acess to the user without authentication");
                return null; 
        }
        Object principal = auth.getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getTokenTMDB();
        } else {
            return principal.toString();
        }
    }

    /**
     * Searches for a movie by its title, is language is set to English, include adult content and 
     * we limit the number of results to one page. This we call the TMDB API with this parameters.
     * The response is logged before being returned.
     * 
     * @param newMovie the movie object containing the title to search for.
     * @return a Mono emitting the API response as a String.
     */
    //TODO LOOK TO SEE IF IT'S USEFUL
	public Mono<String> getOneMovie(Movie newMovie) {
        log.info("APPEL DE L'API POUR LE FILM: " + newMovie.getTitle());
        String token = getConnectedUsername();
        if (token == null) {
            return Mono.error(new RuntimeException("Token user not available (user not connected)")); 
        }
        Mono<String> result = this.webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/search/movie")
                .queryParam("query", newMovie.getTitle())
                .queryParam("include_adult", true)
                .queryParam("language", "en-US")
                .queryParam("page", 1)
                .build())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .retrieve()
            .bodyToMono(String.class);
        log.info("REPONSE DE L'API : " + result);   
        return result;
	}

    /**
     * Retrieves a movie by its ID from the TMDB API.
     * The method calls the TMDB API with the provided movie ID and returns the response as a Movie object wrapped in a Mono.
     * The response is logged before being returned.
     * 
     * @param id the ID of the movie to retrieve.
     * @return a Mono emitting the Movie object retrieved from the API.
     */
    public Mono<Movie> getOneMovie(Long id) {
        log.info("APPEL DE L'API POUR LE FILM AVEC ID: " + id);
        String token = getConnectedUsername();
        if (token == null) {
            return Mono.error(new RuntimeException("Token user not available (user not connected)")); 
        }
        Mono<Movie> result = this.webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/movie/" + id)
                .queryParam("append_to_response", "credits")
                .build())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .retrieve()
            .bodyToMono(MovieDTO.class)
            .map(dto -> {
                Movie movie = movieMapper.toEntity(dto); 
                List<People_movie> actors = peopleMovieMapper.mapCastToPeopleMovie(dto.getCredits().getCast(), movie);
                List<People_movie> crew = peopleMovieMapper.mapCrewToPeopleMovie(dto.getCredits().getCrew(), movie);
                movie.setCredits(Stream.concat(actors.stream(), crew.stream()).collect(Collectors.toList()));
                return movie;
            });
        log.info("REPONSE DE L'API : " + result);
        return result;
    }

    /**
     * Searches for movies using the provided query string.
     * The method calls the TMDB API with the provided query string and returns the response as a String wrapped in a Mono. 
     * We use the search/movie endpoint of the TMDB API.
     * The response is logged before being returned.
     * 
     * @param query the query string to search for movies.
     * @return a Mono emitting the API response as a String.
     */
    public Mono<String> searchMovie(String query) {
        log.info("APPEL DE L'API POUR CHERHCER: " + query);
        String token = getConnectedUsername();
        if (token == null) {
            return Mono.error(new RuntimeException("Token user not available (user not connected)")); 
        }
        log.info("TOKEN: " + token);
        Mono<String> result = this.webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/search/movie")
                .queryParam("query",query)
                .build())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .retrieve()
            .bodyToMono(String.class);
        log.info("REPONSE DE L'API : " + result);
        return result;
    }
}
