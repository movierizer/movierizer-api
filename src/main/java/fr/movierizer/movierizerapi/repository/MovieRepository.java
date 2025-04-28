package fr.movierizer.movierizerapi.repository;

import fr.movierizer.movierizerapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


/*For each method (create or provided in JpaRepository) it will provide a query  */
public interface MovieRepository extends JpaRepository<Movie, Long> {

}