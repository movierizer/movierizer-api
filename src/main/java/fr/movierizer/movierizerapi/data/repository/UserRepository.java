package fr.movierizer.movierizerapi.data.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.movierizer.movierizerapi.data.entities.User;

/*For each method (create or provided in JpaRepository) it will provide a query  */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}