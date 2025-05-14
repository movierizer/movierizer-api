package fr.movierizer.movierizerapi.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.movierizer.movierizerapi.data.entities.Movie;


/*For each method (create or provided in JpaRepository) it will provide a query  */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}