package fr.movierizer.movierizerapi.data.DTO;

import java.util.List;

import fr.movierizer.movierizerapi.data.entities.People;
import fr.movierizer.movierizerapi.data.entities.People_movie;

public class MovieDTO {
    
	private Long id; //this id is the primary key and we use the TMDB id 
	private String title; //title of the movie in the user language
	private String overview;//the synopsis of the movie
	private String original_title; //title of the movie in the original language
	private String release_date; 
	private String poster_path; //the url of the poster of the movie in the TMDB API
	private String backdrop_path; //the url of the backdrop of the movie in the TMDB API
	private Long budget; //the budget to make the movie
	private Long revenue; //all the money made by the movie
	private Long runtime; //the duration of the movie in minutes
	private String country;
	private String url_trailer;
    private CreditsDTO credits;
    
    public MovieDTO() {
    }

    public MovieDTO(Long id, String title, String overview, String original_title, String release_date,
            String poster_path, String backdrop_path, Long budget, Long revenue, Long runtime, String country,
            String url_trailer, CreditsDTO credits) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.original_title = original_title;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.budget = budget;
        this.revenue = revenue;
        this.runtime = runtime;
        this.country = country;
        this.url_trailer = url_trailer;
        this.credits = credits;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getOriginal_title() {
        return original_title;
    }
    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }
    public String getRelease_date() {
        return release_date;
    }
    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
    public String getPoster_path() {
        return poster_path;
    }
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
    public String getBackdrop_path() {
        return backdrop_path;
    }
    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
    public Long getBudget() {
        return budget;
    }
    public void setBudget(Long budget) {
        this.budget = budget;
    }
    public Long getRevenue() {
        return revenue;
    }
    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }
    public Long getRuntime() {
        return runtime;
    }
    public void setRuntime(Long runtime) {
        this.runtime = runtime;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getUrl_trailer() {
        return url_trailer;
    }
    public void setUrl_trailer(String url_trailer) {
        this.url_trailer = url_trailer;
    }

    public CreditsDTO getCredits() {
        return credits;
    }
    public void setCredits(CreditsDTO credits) {
        this.credits = credits;
    }

    
}
