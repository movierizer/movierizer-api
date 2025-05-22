package fr.movierizer.movierizerapi.data.DTO;

import java.util.UUID;

public class DtoMovieUser {
    
    private UUID iduser;
    private Boolean watchlist;
    private Integer grade;
	private Long idmovie; 
	private String title; 
	private String overview;
	private String original_title; 
	private String release_date; 
	private String poster_path; 
	private String backdrop_path; 
	private int budget; 
	private int revenue; 
	private int runtime;

    public DtoMovieUser() {}

    public DtoMovieUser(UUID iduser, Boolean watchlist, Integer grade, Long idmovie, String title, String overview,
            String original_title, String release_date, String poster_path, String backdrop_path, int budget,
            int revenue, int runtime) {
        this.iduser = iduser;
        this.watchlist = watchlist;
        this.grade = grade;
        this.idmovie = idmovie;
        this.title = title;
        this.overview = overview;
        this.original_title = original_title;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.budget = budget;
        this.revenue = revenue;
        this.runtime = runtime;
    }

    public DtoMovieUser(Long idmovie, String title, String overview,
        String original_title, String release_date, String poster_path, String backdrop_path, int budget,
        int revenue, int runtime) {
        this.idmovie = idmovie;
        this.title = title;
        this.overview = overview;
        this.original_title = original_title;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.budget = budget;
        this.revenue = revenue;
        this.runtime = runtime;
    }

    public UUID getIduser() {
        return iduser;
    }

    public void setIduser(UUID iduser) {
        this.iduser = iduser;
    }

    public Boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Boolean watchlist) {
        this.watchlist = watchlist;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Long getIdmovie() {
        return idmovie;
    }

    public void setIdmovie(Long idmovie) {
        this.idmovie = idmovie;
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

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    
    
}
