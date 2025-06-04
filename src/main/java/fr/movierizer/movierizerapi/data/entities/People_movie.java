package fr.movierizer.movierizerapi.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "people_movies")
public class People_movie {
    
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Id Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonIgnore
    private Movie movie; 

    @ManyToOne 
    @JoinColumn(name = "people_id", nullable = false) 
    private People people; 

    @Column(name = "character")
    private String character;

    @Column(name = "job")
    private String job;

    public People_movie() {}

    public People_movie(Long id, People people, Movie movie, String character, String job) {
        this.id = id;
        this.people = people;
        this.movie = movie;
        this.character = character;
        this.job = job;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    
}
