package fr.movierizer.movierizerapi.data.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.movierizer.movierizerapi.data.entities.User_movies;


/*For each method (create or provided in JpaRepository) it will provide a query  */
@Repository
public interface User_movieRepository extends JpaRepository<User_movies, Long> {
        User_movies findByUserIdAndMovieId(UUID user_id, Long movie_id);
        List<User_movies> findByUserIdAndWatchlist(UUID user_id, boolean watchlist);
        List<User_movies> findByUserIdAndWatchlistNotNull(UUID user_id);
}