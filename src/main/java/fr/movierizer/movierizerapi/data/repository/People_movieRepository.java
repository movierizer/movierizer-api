package fr.movierizer.movierizerapi.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.movierizer.movierizerapi.data.entities.People_movie;


/*For each method (create or provided in JpaRepository) it will provide a query  */
@Repository
public interface People_movieRepository extends JpaRepository<People_movie, Long> {
        People_movie findByPeopleIdAndMovieId(Long people_id, Long movie_id);
}