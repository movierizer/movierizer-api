package fr.movierizer.movierizerapi.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import fr.movierizer.movierizerapi.model.Movie;
import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private final WebClient webClient;
    private final String BEARER_TOKEN_TDMB = "Bearer " + System.getenv("BEARER_TOKEN_TDMB");

    /* This constructor is used to create an instance of ApiService and initialize the WebClient with the necessary configuration */
	public ApiService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder
            .baseUrl(System.getenv("API_TMDB_URL_SOURCE"))
            .defaultHeader(HttpHeaders.AUTHORIZATION, BEARER_TOKEN_TDMB) // add of token header
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build();
	}

    /**
     * Searches for a movie by its title, is language is set to English, include adult content and 
     * we limit the number of results to one page. This we call the TMDB API with this parameters.
     * The response is logged before being returned.
     * @param newMovie the movie object containing the title to search for.
     * @return a Mono emitting the API response as a String.
     */
    //LOOK TO SEE IF IT'S USEFUL
	public Mono<String> getOneMovie(Movie newMovie) {
        System.out.println("APPEL DE L'API POUR LE FILM: " + newMovie.getTitle());
        Mono<String> result = this.webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/search/movie")
                .queryParam("query", newMovie.getTitle())
                .queryParam("include_adult", true)
                .queryParam("language", "en-US")
                .queryParam("page", 1)
                .build())
            .retrieve()
            .bodyToMono(String.class);
        System.out.println("REPONSE DE L'API : " + result);
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
        System.out.println("APPEL DE L'API POUR LE FILM AVEC ID: " + id);
        Mono<Movie> result = this.webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/movie/" + id)
                .build())
            .retrieve()
            .bodyToMono(Movie.class);
        System.out.println("REPONSE DE L'API : " + result);
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
        System.out.println("APPEL DE L'API POUR CHERHCER: " + query);
        Mono<String> result = this.webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/search/movie")
            .queryParam("query",query)
            .build())
        .retrieve()
        .bodyToMono(String.class);
        System.out.println("REPONSE DE L'API : " + result);
        return result;
    }
}
