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

	public ApiService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder
            .baseUrl(System.getenv("API_TMDB_URL_SOURCE"))
            .defaultHeader(HttpHeaders.AUTHORIZATION, BEARER_TOKEN_TDMB) // add of token header
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build();
	}

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
}
