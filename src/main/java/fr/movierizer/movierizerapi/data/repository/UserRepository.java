package fr.movierizer.movierizerapi.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.movierizer.movierizerapi.data.entities.User;

/*For each method (create or provided in JpaRepository) it will provide a query  */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}