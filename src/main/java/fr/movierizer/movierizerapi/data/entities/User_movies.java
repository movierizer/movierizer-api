package fr.movierizer.movierizerapi.data.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User_movies {

    @Column(name = "id_user_movie")
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private @Id Long id_user_movie;

    @Column(name = "user_id")
	private UUID userId;

    @Column(name = "movie_id")
    
    private Long movieId;

    @Column(name = "watchlist")
    private String watchlist;

    @Column(name = "grade")
    private Integer grade;
    
    public User_movies() {
    }

    public User_movies(UUID userId, Long movieId, String watchlist, Integer grade) {
        this.userId = userId;
        this.movieId = movieId;
        this.watchlist = watchlist;
        this.grade = grade;
    }

    public UUID getId() {
        return userId;
    }

    public void setId(UUID userId) {
        this.userId = userId;
    }

    public Long getMovie_id() {
        return movieId;
    }

    public void setMovie_id(Long movieId) {
        this.movieId = movieId;
    }

    public String getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(String watchlist) {
        this.watchlist = watchlist;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }


}