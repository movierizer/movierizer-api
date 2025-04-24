package fr.movierizer.movierizerapi.repository;

import fr.movierizer.movierizerapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


/*Pour chaque méthode (créer ou fourni dans JpaRepository) il va fournir une requête  */
public interface MovieRepository extends JpaRepository<Movie, Long> {

}